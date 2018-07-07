/**
 * This file was generated at Sat Jul 07 13:31:56 IST 2018*Don't you dare edit
 * this file.You will regret it if you do!!!* This file is part of hawkeye*
 * CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.*
 */package org.cricket.hawkeye.db;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Set;
import java.util.TreeSet;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.cricket.hawkeye.service.CommonServiceImpl;
import org.cricket.hawkeye.service.ICommonService;

/**
 * ** @author msahu
 */
public class Players implements Externalizable {

    private static final Players theInstance = new Players();
    private Set<Player> all = null;
    private final String FILE = "Players.tab";
    ICommonService commonService = new CommonServiceImpl();

    private Players() {
        init();
    }

    public static Players getInstance() {
        return theInstance;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public Set<Player> getAll() {
        return all;
    }

    public boolean init() {
        Set<Player> result = null;
        FileInputStream fi = null;
        ObjectInputStream si = null;
        GZIPInputStream gi = null;
        String tabPath = this.getCommonService().getTabPath();
        String playerTabPath = tabPath + File.separator + FILE;
        try {
            fi = new FileInputStream(playerTabPath);
            gi = new GZIPInputStream(fi);
            si = new ObjectInputStream(gi);
            result = (Set<Player>) si.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (si != null) {
                    si.close();
                }
                if (gi != null) {
                    gi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        all = result != null ? result : new TreeSet<Player>();
        return true;
    }

    public boolean insert(Player player) {
        return all.add(player);
    }

    public boolean update(Player player) {
        boolean result = true;
        if (all.contains(player)) {
            all.remove(player);
            all.add(player);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean delete(Player player) {
        return all.remove(player);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(all);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        all = (Set<Player>) in.readObject();
    }

    public void flush() {
        FileOutputStream fo = null;
        ObjectOutputStream so = null;
        GZIPOutputStream go = null;
        try {
            String tabPath = this.getCommonService().getTabPath();
            String playerTabPath = tabPath + File.separator + FILE;
            fo = new FileOutputStream(playerTabPath);
            go = new GZIPOutputStream(fo) {
                {
                    def.setLevel(Deflater.BEST_COMPRESSION);
                }
            };
            so = new ObjectOutputStream(go);
            so.writeObject(all);
            so.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (so != null) {
                    so.close();
                }
                if (go != null) {
                    go.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
