/** This file was generated at Fri Feb 20 21:42:21 IST 2026*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) BigBang<->BigCrunch
 * Manoranjan Sahu, All Rights are left.*
 */package org.cricket.hawkeye.db;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Ground extends org.cricket.hawkeye.values.ground.Ground implements Externalizable {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Object obj) {
        return super.compareTo(obj);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.getCountry());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((java.lang.String) in.readObject());
        this.setCountry((org.cricket.hawkeye.db.Country) in.readObject());
    }
}
