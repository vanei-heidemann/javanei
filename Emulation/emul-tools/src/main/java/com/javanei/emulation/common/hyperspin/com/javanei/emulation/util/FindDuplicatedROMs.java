package com.javanei.emulation.common.hyperspin.com.javanei.emulation.util;

import java.io.File;
import java.util.*;

/**
 * Created by Vanei on 16/09/2015.
 */
public class FindDuplicatedROMs {
    public static void main(String[] args) {
        File srcDir = new File("J:\\Emulation\\ALLROMs\\SNK Neo Geo Pocket Color\\Roms");
        try {
            Map<String, List<ROMForNaming>> allRoms = findDuplicated(srcDir);
            //List<ROMForNaming> roms = filterROMs(allRoms);
            //System.out.println("\n\n" + roms);

            int ok = 0;
            int nok = 0;
            for (List<ROMForNaming> roms : allRoms.values()) {
                sortROMs(roms);
                System.out.println(roms.get(0));
                ok++;
                if (roms.size() > 1) {
                    for (int i = 1; i < roms.size(); i++) {
                        System.out.println("  - " + roms.get(i));
                        nok++;
                        File file = new File(srcDir, roms.get(i).fullName + ".zip");
                        file.delete();
                    }
                }
            }
            System.out.println("ROMs unicas...: " + ok);
            System.out.println("ROMs excluidas: " + nok);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<ROMForNaming> filterROMs(Map<String, List<ROMForNaming>> romsMap) throws Exception {
        List<ROMForNaming> result = new LinkedList<>();
        for (List<ROMForNaming> roms : romsMap.values()) {
            result.add(filterROM(roms));
        }
        return result;
    }

    private static void sortROMs(List<ROMForNaming> roms) throws Exception {
        if (roms.size() == 1) {
            // Só tem 1, então não há o que fazer!
            return;
        }

        Collections.sort(roms, new Comparator<ROMForNaming>() {
            @Override
            public int compare(ROMForNaming o1, ROMForNaming o2) {
                // Dá preferencia para as legais
                if (o1.legal != o2.legal) {
                    if (o1.legal) return -1;
                    else return 1;
                }

                // Da preferencia para as que não tem informações adicionais.
                if (o1.additional == null) {
                    if (o2.additional != null) {
                        return -1;
                    }
                } else if (o2.additional == null) {
                    return 1;
                }

                // Classifica pela região
                if (o1.region != null) {
                    if (o2.region == null) {
                        return -1;
                    } else {
                        int i1 = ROMNamingConvention.REGION_LIST.indexOf(o1.region);
                        if (i1 < 0) i1 = 999;
                        int i2 = ROMNamingConvention.REGION_LIST.indexOf(o2.region);
                        if (i2 < 0) i2 = 999;
                        if (i1 < i2) {
                            return -1;
                        } else if (i2 < i1) {
                            return 1;
                        }
                    }
                } else if (o2.region != null) {
                    return 1;
                }

                // Classifica pela versão
                if (o1.version != null) {
                    if (o2.version == null) {
                        if (o1.version.startsWith("(Beta") || o1.version.startsWith("(Proto")
                                || o1.version.startsWith("(Sample)")  || o1.version.startsWith("(Demo)")) {
                            return 1;
                        }
                    } else {
                        int i1 = 999;
                        for (int i = 0; i < ROMNamingConvention.VERSION_LIST.size(); i++) {
                            if (o1.version.matches(ROMNamingConvention.VERSION_LIST.get(i))) {
                                i1 = i;
                            }
                        }
                        int i2 = 999;
                        for (int i = 0; i < ROMNamingConvention.VERSION_LIST.size(); i++) {
                            if (o2.version.matches(ROMNamingConvention.VERSION_LIST.get(i))) {
                                i2 = i;
                            }
                        }
                        if (i1 < i2) {
                            return -1;
                        } else if (i2 < i1) {
                            return 1;
                        } else {
                            int i = o2.version.compareTo(o1.version);
                            if (i != 0) {
                                return i;
                            }
                        }
                    }
                } else if (o2.version != null) {
                    if (o2.version.startsWith("(Beta") || o2.version.startsWith("(Proto")
                            || o2.version.startsWith("(Sample)") || o2.version.startsWith("(Demo)")) {
                        return -1;
                    }
                    return 1;
                }

                // Se tiver versão não licenciada, da preferencia pela versão licenciada
                if (o1.license == null) {
                    if (o2.license != null && o2.license.equals("(Unl)")) {
                        return -1;
                    }
                } else if (o2.license == null) {
                    if (o1.license != null && o1.license.equals("(Unl)")) {
                        return 1;
                    }
                }

                // Ordena pela data
                if (o1.releaseDate != null) {
                    if (o2.releaseDate == null) {
                        return -1;
                    } else {
                        String s1 = o1.releaseDate.substring(1, 5);
                        String s2 = o2.releaseDate.substring(1, 5);
                        int i1 = s1.compareTo(s2);
                        if (i1 != 0) {
                            return i1;
                        }
                    }
                }

                // Medida desesperada, ordena pelo publisher
                if (o1.publisher != null) {
                    if (o2.publisher == null) {
                        return -1;
                    } else {
                        int i1 = ROMNamingConvention.LEGAL_PUBLISHER_LIST.indexOf(o1.publisher);
                        int i2 = ROMNamingConvention.LEGAL_PUBLISHER_LIST.indexOf(o2.publisher);
                        if (i1 < i2) {
                            return -1;
                        } else if (i2 < i1) {
                            return 1;
                        }
                    }
                } else if (o2.publisher != null) {
                    return 1;
                }

                // Ainda mais desesperada, ordena pelos publisher ilegal
                if (o1.publisher != null) {
                    if (o2.publisher == null) {
                        return -1;
                    } else {
                        int i1 = ROMNamingConvention.ILLEGAL_PUBLISHER_LIST.indexOf(o1.publisher);
                        int i2 = ROMNamingConvention.ILLEGAL_PUBLISHER_LIST.indexOf(o2.publisher);
                        if (i1 < i2) {
                            return -1;
                        } else if (i2 < i1) {
                            return 1;
                        }
                    }
                } else if (o2.publisher != null) {
                    return 1;
                }

                System.out.println("ERRO: Não conseguiu diferenciar:");
                System.out.println(o1);
                System.out.println(o2);
                System.out.println("=============================");
                return 0;
            }
        });
    }

    private static ROMForNaming filterROM(List<ROMForNaming> roms) throws Exception {
        sortROMs(roms);
        return roms.get(0);
    }

    private static Map<String, List<ROMForNaming>> findDuplicated(File dir) throws Exception {
        File[] files = dir.listFiles();
        List<String> games = new LinkedList<String>();
        Map<String, List<ROMForNaming>> roms = new LinkedHashMap<>();

        for (File f : files) {
            if (f.getName().endsWith(".zip")) {
                String g = f.getName().substring(0, f.getName().length() - 4);
                games.add(g);
                ROMForNaming rom = new ROMForNaming();
                rom.fullName = g;
                int pos = g.indexOf("(");
                while (pos >= 0) {
                    int endPos = g.indexOf(")", pos);
                    if (endPos > 0) {
                        valida_block:
                        {
                            String s = g.substring(pos, endPos + 1);
                            if (ROMNamingConvention.IGNORE_LIST.contains(s)) {
                                break valida_block;
                            }

                            if (rom.region == null) {
                                if (ROMNamingConvention.REGION_LIST.contains(s)) {
                                    rom.region = s;
                                    rom.name = g.substring(0, pos).trim();
                                    break valida_block;
                                } else {
                                    System.out.println("WARN: Nao identificada String " + s + " para a ROM: " + g);
                                }
                            }

                            if (rom.version == null) {
                                for (String regex : ROMNamingConvention.VERSION_LIST) {
                                    if (s.matches(regex)) {
                                        rom.version = s;
                                        break valida_block;
                                    }
                                }
                            }

                            if (rom.license == null) {
                                if (ROMNamingConvention.LICENSE_LIST.contains(s)) {
                                    rom.license = s;
                                    break valida_block;
                                }
                            }

                            if (rom.publisher == null) {
                                if (ROMNamingConvention.LEGAL_PUBLISHER_LIST.contains(s)) {
                                    rom.publisher = s;
                                    break valida_block;
                                } else if (ROMNamingConvention.ILLEGAL_PUBLISHER_LIST.contains(s)) {
                                    rom.publisher = s;
                                    rom.legal = false;
                                    break valida_block;
                                }
                            }

                            if (rom.additional == null) {
                                if (ROMNamingConvention.ADDITIONAL_LIST.contains(s)) {
                                    rom.additional = s;
                                    break valida_block;
                                }
                            }

                            if (rom.releaseDate == null) {
                                for (String rd : ROMNamingConvention.RELEASE_DATE_LIST) {
                                    if (s.matches(rd)) {
                                        rom.releaseDate = s;
                                        break valida_block;
                                    }
                                }
                            }

                            if (rom.language == null) {
                                for (String idioma : ROMNamingConvention.LANGUAGE_LIST) {
                                    String regex = ".*\\(.*" + idioma + ".*\\).*";
                                    if (s.matches(regex)) {
                                        rom.language = s;
                                        break valida_block;
                                    }
                                }
                            }

                            System.out.println("WARN: Nao identificada String " + s + " para a ROM: " + g);
                        }
                    }

                    pos = g.indexOf("(", endPos);
                }

                List<ROMForNaming> l = roms.get(rom.name);
                if (l == null) {
                    l = new LinkedList<>();
                    roms.put(rom.name, l);
                }
                l.add(rom);
            }
        }

        return roms;
    }
}
