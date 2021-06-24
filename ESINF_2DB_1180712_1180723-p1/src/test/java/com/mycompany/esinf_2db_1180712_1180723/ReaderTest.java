/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_2db_1180712_1180723;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lulu
 */
public class ReaderTest {
    
    public ReaderTest() {
    }

    /**
     * Test of read method, of class Reader.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        Map<Pais, Set<Pais>> m=Reader.read();
        
        Pais por=new Pais("portugal", "europa", (float) 10.31, "lisboa", (float) 38.7071631, (float) -9.135517);
        Pais den=new Pais("dinamarca", "europa", (float) 5.75, "copenhaga", (float) 55.6762944, (float) 12.5681157);
        Pais col=new Pais("colombia", "americasul", (float) 46.86, "bogota", (float) 4.6097100, (float) -74.0817500);
        Pais fin=new Pais("finlandia", "europa", (float) 5.5, "helsinque", (float) 60.1698791, (float) 24.9384078);
        Pais ita=new Pais("italia", "europa", (float) 60.59, "roma", (float) 41.8954656, (float) 12.4823243);
        Pais uru=new Pais("uruguai", "americasul", (float) 3.35, "montevideu", (float) -34.9032800, (float) -56.1881600);
        Pais chi=new Pais("chile", "americasul", (float) 16.80, "santiago", (float) -33.4569400, (float) -70.6482700);
        Pais ukr=new Pais("ucrania", "europa", (float) 42.59, "kiev", (float) 50.440951, (float) 30.5271814);
        Pais gfr=new Pais("guianafrancesa", "americasul", (float) 2.88, "caiena", (float) 4.9333300, (float) -52.3333300);
        Pais brs=new Pais("brasil", "americasul", (float) 206.12, "brasilia", (float) -15.7797200, (float) -47.9297200);
        Pais equ=new Pais("equador", "americasul", (float) 14.88, "quito", (float) -0.2298500, (float) -78.5249500);
        Pais per=new Pais("peru", "americasul", (float) 28.22, "lima", (float) -12.0431800, (float) -77.0282400);
        Pais ven=new Pais("venezuela", "americasul", (float) 31.02, "caracas", (float) 10.4880100, (float) -66.8791900);
        
        ArrayList<Pais> paisList=new ArrayList<>();
        paisList.add(por);
        paisList.add(den);
        paisList.add(col);
        paisList.add(fin);
        paisList.add(ita);
        paisList.add(uru);
        paisList.add(chi);
        paisList.add(ukr);
        paisList.add(gfr);
        paisList.add(brs);
        paisList.add(equ);
        paisList.add(per);
        paisList.add(ven);
        
        boolean result=false, expresult=true;
        for (Pais p : paisList) {
            if (m.keySet().contains(p)) {
                result=true;
            } else {
                result=false;
                break;
            }
        }
        if (result) {
            Set<Pais> colSet=new TreeSet<>();
            colSet.add(brs);
            colSet.add(equ);
            colSet.add(per);
            colSet.add(ven);
            Set<Pais> colSetAct=m.get(col);
            Iterator<Pais> i=colSet.iterator();
            while (i.hasNext()) {
                if (!colSetAct.contains(i.next())) {
                    result=false;
                    break;
                }
            }
        }
        assertEquals(expresult, result);
    }
    
}
