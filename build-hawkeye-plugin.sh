#!/bin/bash
set -e

############################################
#           CONFIGURATION
############################################

VERSION="26.03"

WORKSPACE="$(pwd)"
HOME_DIR="$HOME"
DEPLOY_BASE="$HOME_DIR/j-hawk/plugin"
DEPLOY_DIR="$DEPLOY_BASE/hawkeye-$VERSION"

# Detect repo root (mywork)
REPO_ROOT="$(cd "$WORKSPACE/../.." && pwd)"

JHAWK_MVN_DIR="$REPO_ROOT/j-hawk/j-hawk-code/mvn"
HAWKEYE_DIR="$REPO_ROOT/hawkeye/hawkeye"
ESPN_DIR="$REPO_ROOT/espn-hawk-eye/espn-hawk-eye-code"

############################################
echo "=============================================="
echo "        Hawk-Eye Plugin Build & Deploy"
echo "=============================================="
echo "Version        : $VERSION"
echo "Workspace      : $WORKSPACE"
echo "Repo Root      : $REPO_ROOT"
echo "Deploy Base    : $DEPLOY_BASE"
echo "Deploy Dir     : $DEPLOY_DIR"
echo "=============================================="
echo ""

############################################
# BUILD j-hawk CORE
############################################
echo "▶ Building j-hawk core modules..."
cd "$JHAWK_MVN_DIR"
mvn clean install -DskipTests
echo "✔ j-hawk core built successfully."
echo ""

############################################
# BUILD hawkeye plugin
############################################
echo "▶ Building hawkeye plugin..."
cd "$HAWKEYE_DIR"
mvn clean package -DskipTests

HAWKEYE_JAR="$HAWKEYE_DIR/target/original-hawkeye-$VERSION.jar"

if [ ! -f "$HAWKEYE_JAR" ]; then
    echo "❌ ERROR: hawkeye jar not found at $HAWKEYE_JAR"
    exit 1
fi

echo "✔ hawkeye jar built: $HAWKEYE_JAR"
echo ""

############################################
# BUILD espn provider
############################################
echo "▶ Building espn-hawk-eye provider..."
cd "$ESPN_DIR"
mvn clean package -DskipTests

ESPN_JAR="$ESPN_DIR/target/espnhawkeye-$VERSION.jar"

if [ ! -f "$ESPN_JAR" ]; then
    echo "❌ ERROR: espnhawkeye jar not found at $ESPN_JAR"
    exit 1
fi

echo "✔ espnhawkeye jar built: $ESPN_JAR"
echo ""

############################################
# PREPARE DEPLOYMENT DIRECTORY
############################################
echo "▶ Preparing deployment directory..."
rm -rf "$DEPLOY_DIR"
mkdir -p "$DEPLOY_DIR"
echo "✔ Clean deployment directory created."
echo ""

############################################
# COPY JARS
############################################
echo "▶ Copying plugin jars..."
cp "$HAWKEYE_JAR" "$DEPLOY_DIR/hawkeye-$VERSION.jar"
cp "$ESPN_JAR" "$DEPLOY_DIR/"
echo "✔ Jars copied."
echo ""

############################################
# COPY CONFIG + DATA FILES
############################################
echo "▶ Copying configuration and data files..."
cp "$WORKSPACE/config.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/metadata.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/plugin.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/"*.tab "$DEPLOY_DIR/"
echo "✔ Config and data files copied."
echo ""

############################################
# COPY AI SURFACE
############################################
echo "▶ Copying AI surface definition..."

if [ -d "$WORKSPACE/ai" ]; then
    cp -r "$WORKSPACE/ai" "$DEPLOY_DIR/"
    echo "✔ AI directory copied."
else
    echo "⚠ WARNING: ai/ directory not found in workspace."
fi

# Validate prompt file exists
if [ ! -f "$DEPLOY_DIR/ai/prompt.txt" ]; then
    echo "❌ ERROR: ai/prompt.txt not found in deployment!"
    echo "AI surface definition is mandatory."
    exit 1
fi

echo "✔ AI prompt file validated."
echo ""

############################################
# CREATE ZIP PACKAGE
############################################
echo "▶ Creating plugin zip package..."
cd "$DEPLOY_BASE"
rm -f "hawkeye-$VERSION.zip"

jar -cf "hawkeye-$VERSION.zip" "hawkeye-$VERSION"

echo "✔ Plugin zip created: $DEPLOY_BASE/hawkeye-$VERSION.zip"
echo ""

############################################
# FINAL VERIFICATION
############################################
echo "=============================================="
echo "Deployment Folder Structure:"
echo "----------------------------------------------"
ls -R "$DEPLOY_DIR"
echo "----------------------------------------------"
echo "✔ Hawk-Eye Plugin $VERSION deployed successfully."
echo "=============================================="
