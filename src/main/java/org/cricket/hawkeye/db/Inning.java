/**
 * This file was generated at Sat Aug 05 12:53:00 IST 2017*Don't you dare edit
 * this file.You will regret it if you do!!!* This file is part of hawkeye*
 * CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.*
 */package org.cricket.hawkeye.db;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Inning extends org.cricket.hawkeye.values.inning.Inning implements Externalizable {

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
        out.writeObject(this.getPlayer());
        out.writeObject(this.getRuns());
        out.writeObject(this.getMins());
        out.writeObject(this.getBallFaced());
        out.writeObject(this.getFours());
        out.writeObject(this.getSixes());
        out.writeObject(this.getStrikeRate());
        out.writeObject(this.getPositions());
        out.writeObject(this.getDismissalType());
        out.writeObject(this.getInnings());
        out.writeObject(this.getOpposition());
        out.writeObject(this.getGround());
        out.writeObject(this.getStartDate());
        out.writeObject(this.getOdi());
        out.writeObject(this.getWasOut());
        out.writeObject(this.getBatted());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setPlayer((org.cricket.hawkeye.db.Player) in.readObject());
        this.setRuns((java.lang.Integer) in.readObject());
        this.setMins((java.lang.Integer) in.readObject());
        this.setBallFaced((java.lang.Integer) in.readObject());
        this.setFours((java.lang.Integer) in.readObject());
        this.setSixes((java.lang.Integer) in.readObject());
        this.setStrikeRate((java.lang.Float) in.readObject());
        this.setPositions((java.lang.Integer) in.readObject());
        this.setDismissalType((org.cricket.hawkeye.codegen.constant.DismissalType) in.readObject());
        this.setInnings((java.lang.Integer) in.readObject());
        this.setOpposition((org.cricket.hawkeye.db.Country) in.readObject());
        this.setGround((org.cricket.hawkeye.db.Ground) in.readObject());
        this.setStartDate((java.util.Date) in.readObject());
        this.setOdi((java.lang.Integer) in.readObject());
        this.setWasOut((java.lang.Boolean) in.readObject());
        this.setBatted((java.lang.Boolean) in.readObject());
    }
}
