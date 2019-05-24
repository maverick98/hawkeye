/*
 * This file is part of hawkeye
 * Copyright (C) BigBang<->BigCrunch Manoranjan Sahu, All Rights Reserved.
 *
 * This software is provided under the GNU GPL Version 2. In particular,
 *
 * 1) If you modify a source file, make a comment in it containing your name and the date.
 * 2) If you distribute a modified version, you must do it under the GPL 2.
 * 3) Developers are encouraged but not required to notify the hawkeye maintainers at abeautifulmind98@gmail.com
 * when they make a useful addition. It would be nice if significant contributions could be merged into the main distribution.
 *
 * A full copy of the license can be found in gpl.txt or online at
 * http://www.gnu.org/licenses/gpl.txt
 *
 * END_HEADER
 */
package org.cricket.hawkeye.values.inning;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *
 * @author manosahu
 */
public class InningSerializer extends JsonSerializer<org.cricket.hawkeye.db.Inning> {

    @Override
    public void serialize(org.cricket.hawkeye.db.Inning value, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        jgen.writeStartObject();

        jgen.writeStringField("player", value.player.name);
        jgen.writeNumberField("runs", value.runs);
        jgen.writeNumberField("mins", value.mins);
        jgen.writeNumberField("ballFaced", value.ballFaced);
        jgen.writeNumberField("fours", value.fours);
        jgen.writeNumberField("strikeRate", value.strikeRate);
        jgen.writeNumberField("positions", value.positions);
        jgen.writeObjectField("dismissalType", value.dismissalType);
        jgen.writeNumberField("innings", value.innings);
        jgen.writeStringField("opposition", value.opposition.name);
        jgen.writeStringField("ground", value.ground.name);
        jgen.writeObjectField("startDate", value.startDate);
        jgen.writeNumberField("odi", value.odi);
        jgen.writeBooleanField("wasOut", value.wasOut);
        jgen.writeBooleanField("batted", value.batted);
        jgen.writeObjectField("matchType", value.matchType);

        jgen.writeEndObject();
    }

}
