package com.javanei.emulation.common.hyperspin.tools;

import com.javanei.emulation.common.hyperspin.HSSystem;
import com.javanei.emulation.common.hyperspin.HyperSpinConf;
import com.javanei.emulation.common.hyperspin.util.HSReadConfigs;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Vanei on 13/09/2015.
 */
public class DownloadHSDatabases {
    private static final String HYPERLIST_URL = "http://hyperlist.hyperspin-fe.com/";

    public static void main(String[] args) {
        try {
            downloadDatabases();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void downloadDatabases() throws Exception {
        System.out.println("Fazendo download do HTML HyperList");
        DecimalFormat decimalFormat = new DecimalFormat("###,##0.000");
        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.UK));

        System.out.println("Parseando a página");
        String page = downloadURL(HYPERLIST_URL);
        List<HLItem> sistemas = new LinkedList<HLItem>();
        // Acha o header
        int pos = page.indexOf(">System Name<");
        if (pos > 0) {
            page = page.substring(pos, page.indexOf("</table>", pos));
            List<String> lines = new LinkedList<String>();
            // Identifica cada uma das linhas da tabela
            pos = page.indexOf("<tr");
            while (pos > 0) {
                int endPos = page.indexOf("</tr>", pos);
                String line = page.substring(pos, endPos);
                line = line.substring(line.indexOf("<td"));

                String[] ss = line.split("<td");
                HLItem it = new HLItem();
                // Limpa as linhas em branco
                int ln = 0;
                while (ss[ln].trim().isEmpty()) {
                    ln++;
                }

                // Le o nome do sistema
                String s = ss[ln];
                int endPos1 = s.indexOf("</span");
                int pos1 = s.lastIndexOf(">", endPos1) + 1;
                it.systemName = s.substring(pos1, endPos1);

                // Le o total de jogos
                s = ss[++ln];
                endPos1 = s.indexOf("</span");
                pos1 = s.lastIndexOf(">", endPos1) + 1;
                s = s.substring(pos1, endPos1);
                it.totalGames = decimalFormat.parse(s).intValue();

                // Le a versao
                s = ss[++ln];
                endPos1 = s.indexOf("</span");
                pos1 = s.lastIndexOf(">", endPos1) + 1;
                it.version = s.substring(pos1, endPos1);

                // Le o Last Update
                s = ss[++ln];
                endPos1 = s.indexOf("</span");
                pos1 = s.lastIndexOf(">", endPos1) + 1;
                it.lastUpdate = s.substring(pos1, endPos1);

                // Ignora quem fez o update
                ln++;

                // Le a URL
                s = ss[++ln];
                pos1 = s.indexOf("href");
                pos1 = s.indexOf("'", pos1) + 1;
                endPos1 = s.indexOf("'", pos1);
                it.downloadXML = HYPERLIST_URL + s.substring(pos1, endPos1);

                sistemas.add(it);
                pos = page.indexOf("<tr", endPos);
            }
        }

        for (HLItem it : sistemas) {
            System.out.println("Processando sistema: " + it);
            File dbDir = new File(HyperSpinConf.HS_DATABASE_DIR, it.systemName);
            File dbFile = new File(dbDir, it.systemName + ".xml");
            if (!dbDir.exists() || !dbFile.exists()) {
                System.out.println("  Não existe o banco de dados para o sistema " + it.systemName);
                downloadXML(it);
            } else {
                HSSystem system = HSReadConfigs.getSystem(it.systemName);
                if (!system.lastlistupdate.equals(it.lastUpdate)) {
                    System.out.println("  A data de atualizacao esta diferente para o sistema " + it.systemName
                            + " :: " + system.lastlistupdate + "->" + it.lastUpdate);
                    downloadXML(it);
                }
            }
        }
    }

    private static void downloadXML(HLItem system) throws Exception {
        System.out.println("    Baixando XML para o sistema " + system.systemName);
        File dbDir = new File(HyperSpinConf.HS_DATABASE_DIR, system.systemName);
        File dbFile = new File(dbDir, system.systemName + ".xml");

        String xml = downloadURL(system.downloadXML);
        // Corrige um erro no Commodore 64 & Plus4
        xml = xml.replaceAll("& ", "&amp; ");
        //xml = xml.replaceAll("<description>\"", "<description>").replaceAll("\"</description>", "</description>");
        // Corrige um erro no xml do Future Pinball
        xml = xml.replaceAll("image=\"\"\"", "image=\"\"");
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(dbFile);
        out.write(xml.getBytes());
        out.flush();
        out.close();
    }

    private static String downloadURL(String downloadURL) throws Exception {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        StringBuilder sbPage = new StringBuilder();
        try {
            url = new URL(downloadURL);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                sbPage.append(line).append("\r\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception ioe) {
            }
        }
        return sbPage.toString();
    }

    static class HLItem {
        public String systemName;
        public int totalGames;
        public String version;
        public String lastUpdate;
        public String downloadXML;
        //public String genreXML;

        @Override
        public String toString() {
            return "HLItem {" +
                    "systemName='" + systemName + '\'' +
                    ", totalGames=" + totalGames +
                    ", version='" + version + '\'' +
                    ", lastUpdate='" + lastUpdate + '\'' +
                    ", downloadXML='" + downloadXML + '\'' +
                    //", genreXML='" + genreXML + '\'' +
                    '}';
        }
    }
}
