/**
 * This file was generated at Tue Feb 23 13:48:11 IST 2021*Don't you dare edit this file.You will regret it if you do!!!* This file is part of hawkeye* CopyLeft (C) 2012-2013 Manoranjan Sahu, All Rights are left.*
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
public class Innings implements Externalizable {

    private static final Innings theInstance = new Innings();
    private Set<Inning> all = null;
    private final String FILE = "Innings.tab";
    ICommonService commonService = new CommonServiceImpl();

    private Innings() {
        init();
    }

    public static Innings getInstance() {
        return theInstance;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public Set<Inning> getAll() {
        return all;
    }

    public boolean init() {
        Set<Inning> result = null;
        FileInputStream fi = null;
        ObjectInputStream si = null;
        GZIPInputStream gi = null;
        String tabPath = this.getCommonService().getTabPath();
        String inningTabPath = tabPath + File.separator + FILE;
        try {
            fi = new FileInputStream(inningTabPath);
            gi = new GZIPInputStream(fi);
            si = new ObjectInputStream(gi);
            result = (Set<Inning>) si.readObject();
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
        all = result != null ? result : new TreeSet<Inning>();
        return true;
    }

    public boolean insert(Inning inning) {
        return all.add(inning);
    }

    public boolean update(Inning inning) {
        boolean result = true;
        if (all.contains(inning)) {
            all.remove(inning);
            all.add(inning);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean delete(Inning inning) {
        return all.remove(inning);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(all);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        all = (Set<Inning>) in.readObject();
    }

    public void flush() {
        FileOutputStream fo = null;
        ObjectOutputStream so = null;
        GZIPOutputStream go = null;
        try {
            String tabPath = this.getCommonService().getTabPath();
            String inningTabPath = tabPath + File.separator + FILE;
            fo = new FileOutputStream(inningTabPath);
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
