package com.javanei.emulation.common.hyperspin.com.javanei.emulation.util;

import java.util.*;

/**
 * Created by Vanei on 16/09/2015.
 */
public final class ROMNamingConvention {
    public static final List<String> IGNORE_LIST;
    public static final List<String> REGION_LIST;
    public static final List<String> LANGUAGE_LIST;
    public static final List<String> VERSION_LIST;
    public static final List<String> LICENSE_LIST;
    public static final List<String> LEGAL_PUBLISHER_LIST;
    public static final List<String> ILLEGAL_PUBLISHER_LIST;
    public static final List<String> ADDITIONAL_LIST;
    public static final List<String> RELEASE_DATE_LIST;

    // no-intro naming convetion:
    // [BIOS flag] Title (Region) (Languages) (Version) (Devstatus) (Additional) (Special) (License) [Status]

    static {
        IGNORE_LIST = new LinkedList<>();
        IGNORE_LIST.addAll(Arrays.asList(new String[]{"(Yi)", "(NES Test)", "(Sunsoft)", "(Bulletproof)"}));

        REGION_LIST = new LinkedList<>();
        // Oficiais No-Intro
        REGION_LIST.addAll(Arrays.asList(new String[]{"(World)", "(USA, Europe)", "(Japan, USA)",
                "(USA)", "(Europe)", "(Japan, Europe)", "(Brazil)", "(Asia)",
                "(Canada)", "(Australia)", "(Spain)", "(Italy)", "(Japan)", "(China)", "(France)", "(Germany)",
                "(Hong Kong)", "(Korea)", "(Netherlands)", "(Sweden)"
        }));
        // Descobertos
        REGION_LIST.addAll(Arrays.asList(new String[]{"(Unknown)", "(Asia, Australia)", "(Russia)"}));

        LANGUAGE_LIST = new LinkedList<>();
        // Oficiais No-Intro, ordem deve ser respeitada
        LANGUAGE_LIST.addAll(Arrays.asList(new String[]{"En", "Ja", "Fr", "De", "Es", "It",
                "Nl", "Pt", "Sv", "No", "Da", "Fi", "Zh", "Ko", "Pl"}));

        VERSION_LIST = new LinkedList<>();
        VERSION_LIST.addAll(Arrays.asList(new String[]{".*\\(Rev .+?\\).*",
                ".*\\(v.+?\\).*",
                ".*\\(Beta.*\\).*",
                ".*\\(Proto.*\\).*",
                ".*\\(Sample.*\\).*",
                ".*\\(Demo\\).*",
                ".*\\(PAL\\).*",
                ".*\\(NTSC\\).*",
                ".*\\(Final Version\\).*"}));

        LICENSE_LIST = new LinkedList<>();
        LICENSE_LIST.addAll(Arrays.asList(new String[]{"(Unl)"}));

        LEGAL_PUBLISHER_LIST = new LinkedList<>();
        LEGAL_PUBLISHER_LIST.addAll(Arrays.asList(new String[]{"(Namco)", "(Taito)", "(UBI Soft)", "(Victor)",
                "(RCM Group)"}));

        ILLEGAL_PUBLISHER_LIST = new LinkedList<>();
        ILLEGAL_PUBLISHER_LIST.addAll(Arrays.asList(new String[]{"(Hwang Shinwei)", "(Gluk Video)", "(NTDEC, Gluk Video)",
                "(Aladdin Compact Cartridge)", "(ArchiMENdes Hen)", "(Genteiban!)", "(Sachen)", "(Tengen)"}));

        ADDITIONAL_LIST = new LinkedList<>();
        ADDITIONAL_LIST.addAll(Arrays.asList(new String[]{"(RAM)", "(Cart Present)", "(Gold Edition)",
                "(Hacker)", "(Hacker inc.)", "(Plug-Thru Cart)"}));

        RELEASE_DATE_LIST = new LinkedList<>();
        RELEASE_DATE_LIST.addAll(Arrays.asList(new String[]{".*\\(\\d\\d\\d\\d\\).*",
                ".*\\(\\d\\d\\d\\d-\\d\\d-\\d\\d\\).*"}));
    }
}
