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
echo "=========================================="
echo "     Hawk-Eye Plugin Build & Deploy"
echo "=========================================="
echo "Version       : $VERSION"
echo "Workspace     : $WORKSPACE"
echo "Repo Root     : $REPO_ROOT"
echo "Deploy Dir    : $DEPLOY_DIR"
echo ""

############################################
# BUILD j-hawk CORE
############################################
echo "Building j-hawk modules..."
cd "$JHAWK_MVN_DIR"
mvn clean install -DskipTests

############################################
# BUILD hawkeye plugin
############################################
echo ""
echo "Building hawkeye..."
cd "$HAWKEYE_DIR"
mvn clean package -DskipTests

############################################
# BUILD espn provider
############################################
echo ""
echo "Building espn-hawk-eye..."
cd "$ESPN_DIR"
mvn clean package -DskipTests

############################################
# DEPLOY
############################################
echo ""
echo "Preparing deployment folder..."
rm -rf "$DEPLOY_DIR"
mkdir -p "$DEPLOY_DIR"

echo "Copying plugin jars..."

# Use thin jar (original) instead of shaded fat jar
cp "$HAWKEYE_DIR/target/original-hawkeye-$VERSION.jar" \
   "$DEPLOY_DIR/hawkeye-$VERSION.jar"

cp "$ESPN_DIR/target/espnhawkeye-$VERSION.jar" "$DEPLOY_DIR/"


echo "Copying configuration and data files..."
cp "$WORKSPACE/config.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/metadata.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/plugin.xml" "$DEPLOY_DIR/"
cp "$WORKSPACE/"*.tab "$DEPLOY_DIR/"

############################################
# ZIP (Using jar instead of zip)
############################################
echo ""
echo "Creating zip package..."

cd "$DEPLOY_BASE"
rm -f "hawkeye-$VERSION.zip"

jar -cf "hawkeye-$VERSION.zip" "hawkeye-$VERSION"

echo "Zip created using JDK jar tool."
