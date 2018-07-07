/**
 * This file was generated at Sat Jul 07 13:32:00 IST 2018*Don't you dare edit
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
public class Countrys implements Externalizable {

    private static final Countrys theInstance = new Countrys();
    private Set<Country> all = null;
    private final String FILE = "Countrys.tab";
    ICommonService commonService = new CommonServiceImpl();

    private Countrys() {
        init();
    }

    public static Countrys getInstance() {
        return theInstance;
    }

    public ICommonService getCommonService() {
        return commonService;
    }

    public Set<Country> getAll() {
        return all;
    }

    public boolean init() {
        Set<Country> result = null;
        FileInputStream fi = null;
        ObjectInputStream si = null;
        GZIPInputStream gi = null;
        String tabPath = this.getCommonService().getTabPath();
        String countryTabPath = tabPath + File.separator + FILE;
        try {
            fi = new FileInputStream(countryTabPath);
            gi = new GZIPInputStream(fi);
            si = new ObjectInputStream(gi);
            result = (Set<Country>) si.readObject();
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
        all = result != null ? result : new TreeSet<Country>();
        return true;
    }

    public boolean insert(Country country) {
        return all.add(country);
    }

    public boolean update(Country country) {
        boolean result = true;
        if (all.contains(country)) {
            all.remove(country);
            all.add(country);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean delete(Country country) {
        return all.remove(country);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(all);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        all = (Set<Country>) in.readObject();
    }

    public void flush() {
        FileOutputStream fo = null;
        ObjectOutputStream so = null;
        GZIPOutputStream go = null;
        try {
            String tabPath = this.getCommonService().getTabPath();
            String countryTabPath = tabPath + File.separator + FILE;
            fo = new FileOutputStream(countryTabPath);
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
