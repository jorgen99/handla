package solidbeans.com.handla.db;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class DbLineParser {

    public static final String TAG = DbLineParser.class.getSimpleName();

    public static List<Category> constructCategories() {
        String cs = catetoryString();
        return createCategoryList(cs);
    }

    public static List<ItemType> constructItemTypes(List<Category> categories) {
        String its = itemTypeString();
        return createItemTypeList(categories, its);
    }

    @NonNull
    public static List<Category> createCategoryList(String cs) {
        String[] categories = cs.split("\n");
        List<Category> reply = new ArrayList<>();
        for (String category : categories) {
            Category c = createCategory(category);
            reply.add(c.getOrdinal(), c);
        }
        return reply;
    }

    @NonNull
    public static Category createCategory(String category) {
        String[] parts = category.split(";");
        return new Category(parts[1], Integer.parseInt(parts[0]), parts[2]);
    }

    @NonNull
    private static List<ItemType> createItemTypeList(List<Category> categories, String its) {
        String[] itemTypes = its.split("\n");
        return Arrays.stream(itemTypes)
                .map(itemType -> createItemType(itemType, categories))
                .collect(Collectors.toList());
    }

    private static ItemType createItemType(String itemType, List<Category> categories) {
        String[] parts = itemType.split(";");
        String id = parts[0];
        String name = parts[1];
        String cName = parts[2];
        Category c = findCategory(categories, cName);
//        Log.d(TAG, "createItemType: category :" + c);
        return new ItemType(Integer.parseInt(id), name, c);
    }

    private static Category findCategory(List<Category> categories, String cName) {
        for (Category category : categories) {
            if(category.getName().equals(cName)) {
                return category;
            }
        }
        return categories.get(0);
    }

    @NonNull
    private static String catetoryString() {
        return "0;Okategoriserad;#999999\n"+
                "1;Happy Vegan;#C9D8C5\n"+
                "2;Rollands;#86D0A2\n"+
                "3;Fiskbilen;#697B8A\n"+
                "4;Frukt & Grönt;#067843\n"+
                "5;Bröd;#86D0A2\n"+
                "6;Kryddor & Olja;#0665AF\n"+
                "7;Kött & Chark;#86D0A2\n"+
                "8;Ost;#86D0A2\n"+
                "9;Torrvaror;#A60C0C\n"+
                "10;Mejeri;#F6FCFF\n"+
                "11;Läsk & Apmat;#86D0A2\n"+
                "12;Fryst;#2D9BFF\n"+
                "13;Hygien;#86D0A2\n"+
                "14;Godis & Kassan;#86D0A2"+
                "99999;Avklarade;#EEFFEE";
    }

    private static String itemTypeString() {
        return "1;9V batteri;Godis & Kassan\n" +
                "2;Agavesirap;Torrvaror\n" +
                "3;Aluminiumfolie;Torrvaror\n" +
                "4;Amee;Läsk & Apmat\n" +
                "5;Ananas;Frukt & Grönt\n" +
                "6;Ancho;Rollands\n" +
                "7;Annanasringar;Kryddor & Olja\n" +
                "8;Apelsiner;Frukt & Grönt\n" +
                "9;Apelsinjuice;Mejeri\n" +
                "10;Apmat;Läsk & Apmat\n" +
                "11;Aubergine;Frukt & Grönt\n" +
                "12;Avokado - fryst;Fryst\n" +
                "13;Avokado;Frukt & Grönt\n" +
                "14;Avorioris;Torrvaror\n" +
                "15;Babyspenat;Frukt & Grönt\n" +
                "16;Bacon;Kött & Chark\n" +
                "17;Badrumsspray;Hygien\n" +
                "18;Bake-off bröd;Bröd\n" +
                "19;Baked beans;Torrvaror\n" +
                "20;Bakplåtspapper;Torrvaror\n" +
                "21;Bakpotatis;Frukt & Grönt\n" +
                "22;Balsamvinäger;Rollands\n" +
                "23;Bambuskott;Torrvaror\n" +
                "24;Bananer;Frukt & Grönt\n" +
                "25;Bars;Godis & Kassan\n" +
                "26;Basilika;Frukt & Grönt\n" +
                "27;Basmatiris;Torrvaror\n" +
                "28;Bea;Kött & Chark\n" +
                "29;Ben & Jerry;Godis & Kassan\n" +
                "30;Blomkål;Frukt & Grönt\n" +
                "31;Blåbär;Fryst\n" +
                "32;Bondbröd;Bröd\n" +
                "33;Bregott;Mejeri\n" +
                "34;Broccoli;Frukt & Grönt\n" +
                "35;Brysselkål;Frukt & Grönt\n" +
                "36;Bröd;Bröd\n" +
                "37;Bubbelbad;Hygien\n" +
                "38;Bulgur;Torrvaror\n" +
                "39;Buljong;Kryddor & Olja\n" +
                "40;Bulle;Bröd\n" +
                "41;Böngroddar;Frukt & Grönt\n" +
                "42;Bönor;Torrvaror\n" +
                "43;Cashewnötter;Frukt & Grönt\n" +
                "44;Champinjoner;Frukt & Grönt\n" +
                "45;Chevre;Ost\n" +
                "46;Chiabatta;Bröd\n" +
                "47;Chibba;Läsk & Apmat\n" +
                "48;Chili;Frukt & Grönt\n" +
                "49;Chilipulver;Kryddor & Olja\n" +
                "50;Chilisås;Kryddor & Olja\n" +
                "51;Chipotle;Rollands\n" +
                "52;Chips;Läsk & Apmat\n" +
                "53;Choklad;Godis & Kassan\n" +
                "54;Chokladkaka;Godis & Kassan\n" +
                "55;Ciabatta;Bröd\n" +
                "56;Citron;Frukt & Grönt\n" +
                "57;Citrongräs;Frukt & Grönt\n" +
                "58;Citrusdryck;Mejeri\n" +
                "59;Cola zero;Läsk & Apmat\n" +
                "60;Cola;Läsk & Apmat\n" +
                "61;Cornichon;Torrvaror\n" +
                "62;Cous Cous;Torrvaror\n" +
                "63;Creme fraiche;Mejeri\n" +
                "64;Crostini;Bröd\n" +
                "65;Crustini;Bröd\n" +
                "66;Crème Fraîche;Mejeri\n" +
                "67;Cuornfärs;Fryst\n" +
                "68;Curry;Kryddor & Olja\n" +
                "69;Currypasta - grön;Kryddor & Olja\n" +
                "70;Currypasta - röd;Kryddor & Olja\n" +
                "71;Dadlar;Frukt & Grönt\n" +
                "72;Delocatoboll;Bröd\n" +
                "73;Digestive;Bröd\n" +
                "74;Dijonsenap;Kryddor & Olja\n" +
                "75;Dill;Bröd\n" +
                "76;Dipp;Läsk & Apmat\n" +
                "77;Dippkrydda;Kryddor & Olja\n" +
                "78;Diskborste;Hygien\n" +
                "79;Diskmedel;Hygien\n" +
                "80;Drickyoghurt;Mejeri\n" +
                "81;Drottningsylt;Kryddor & Olja\n" +
                "82;Dyra fröknäcke;Bröd\n" +
                "83;Edamamebönor;Fryst\n" +
                "84;Fajitas;Kryddor & Olja\n" +
                "85;Falafel;Fryst\n" +
                "86;Falukorv;Kött & Chark\n" +
                "87;Fanta Zero;Läsk & Apmat\n" +
                "88;Fanta;Läsk & Apmat\n" +
                "89;Feta;Ost\n" +
                "90;Fettuccine;Torrvaror\n" +
                "91;Fil;Mejeri\n" +
                "92;Filmjölk;Mejeri\n" +
                "93;Finn Crisp;Bröd\n" +
                "94;Flingor;Torrvaror\n" +
                "95;Fläsk;Kött & Chark\n" +
                "96;Fläskfilé;Kött & Chark\n" +
                "97;Folie;Torrvaror\n" +
                "98;Frukt;Frukt & Grönt\n" +
                "99;Fry's vegoschitzel;Fryst\n" +
                "100;Fröknäcke;Bröd\n" +
                "101;Fun light;Kryddor & Olja\n" +
                "102;Fänkål;Frukt & Grönt\n" +
                "103;Färsk pasta;Kött & Chark\n" +
                "104;Förrättsgrej;Fiskbilen\n" +
                "105;GB-sandwich;Fryst\n" +
                "106;Gainomax;Godis & Kassan\n" +
                "107;Garam masala;Kryddor & Olja\n" +
                "108;Gemsallad;Frukt & Grönt\n" +
                "109;Gene;Bröd\n" +
                "110;Getost;Ost\n" +
                "111;Gifflar;Bröd\n" +
                "112;Gladpac;Hygien\n" +
                "113;Glass;Fryst\n" +
                "114;Glödlampa;Hygien\n" +
                "115;Gnocchi;Torrvaror\n" +
                "116;Godis;Godis & Kassan\n" +
                "117;Godisrem;Godis & Kassan\n" +
                "118;Gravad lax;Fiskbilen\n" +
                "119;Grillkrydda;Kryddor & Olja\n" +
                "120;Grillsås;Kryddor & Olja\n" +
                "121;Groddar;Frukt & Grönt\n" +
                "122;Grädde;Mejeri\n" +
                "123;Gräddfil;Mejeri\n" +
                "124;Gräslök;Frukt & Grönt\n" +
                "125;Grönkål;Frukt & Grönt\n" +
                "126;Grönsaker;Frukt & Grönt\n" +
                "127;Grönsaksbuljong;Kryddor & Olja\n" +
                "128;Gröt;Kött & Chark\n" +
                "129;Gul lök;Frukt & Grönt\n" +
                "130;Gulash;Torrvaror\n" +
                "131;Gurka;Frukt & Grönt\n" +
                "132;Hallogenlampor;Hygien\n" +
                "133;Hallon - frysta;Fryst\n" +
                "134;Hallonsorbet;Fryst\n" +
                "135;Hallosylt;Kryddor & Olja\n" +
                "136;Halloumi;Ost\n" +
                "137;Hamburgare;Fryst\n" +
                "138;Hamburgerbröd;Bröd\n" +
                "139;Hamburgerdressing;Kryddor & Olja\n" +
                "140;Haricot verts;Frukt & Grönt\n" +
                "141;Hasselnötter;Torrvaror\n" +
                "142;Havregryn;Torrvaror\n" +
                "143;Hela tomater;Torrvaror\n" +
                "144;Hollandaise;Kött & Chark\n" +
                "145;Honung;Torrvaror\n" +
                "146;Hårdost;Ost\n" +
                "147;Indian Tonic;Läsk & Apmat\n" +
                "148;Ingefära;Frukt & Grönt\n" +
                "149;Jalapenos;Kryddor & Olja\n" +
                "150;Japansk soja;Kryddor & Olja\n" +
                "151;Jasminris;Torrvaror\n" +
                "152;Jordgubbar - frysta;Fryst\n" +
                "153;Jordgubbar;Fryst\n" +
                "154;Jordgubbssylt;Kryddor & Olja\n" +
                "155;Jordnötsolja;Kryddor & Olja\n" +
                "156;Jordnötssmör;Frukt & Grönt\n" +
                "157;Jordnötter;Godis & Kassan\n" +
                "158;Jordärtskockor;Frukt & Grönt\n" +
                "159;Jos;Mejeri\n" +
                "160;Julmust;Läsk & Apmat\n" +
                "161;Jäst;Mejeri\n" +
                "162;K-special;Torrvaror\n" +
                "163;Kaffe;Bröd\n" +
                "164;Kaffefilter;Kryddor & Olja\n" +
                "165;Kakao;Torrvaror\n" +
                "166;Kakor;Bröd\n" +
                "167;Kalamata;Torrvaror\n" +
                "168;Kalles kaviar;Mejeri\n" +
                "169;Kamutvete;Torrvaror\n" +
                "170;Kanel;Kryddor & Olja\n" +
                "171;Kanelgifflar;Bröd\n" +
                "172;Kantareller;Frukt & Grönt\n" +
                "173;Kaviar;Mejeri\n" +
                "174;Kebabkött;Kött & Chark\n" +
                "175;Keso;Mejeri\n" +
                "176;Ketchup;Kryddor & Olja\n" +
                "177;Kikärter;Torrvaror\n" +
                "178;Knäckebröd;Bröd\n" +
                "179;Knäckemix;Torrvaror\n" +
                "180;Kokosflingor;Torrvaror\n" +
                "181;Kokosmjölk;Torrvaror\n" +
                "182;Kokossocker;Torrvaror\n" +
                "183;Koriander;Frukt & Grönt\n" +
                "184;Korv;Kött & Chark\n" +
                "185;Korvbröd;Bröd\n" +
                "186;Kronärtskocka;Kryddor & Olja\n" +
                "187;Kronärtskockspesto;Torrvaror\n" +
                "188;Krossade tomater;Torrvaror\n" +
                "189;Krutonger;Bröd\n" +
                "190;Kräftstjärtar;Kött & Chark\n" +
                "191;Kvarg;Mejeri\n" +
                "192;Kyckling;Kött & Chark\n" +
                "193;Kycklingfilé;Kött & Chark\n" +
                "194;Kycklingklubba;Kött & Chark\n" +
                "195;Kycklingklubbor - frysta;Fryst\n" +
                "196;Kycklinglårfilé;Kött & Chark\n" +
                "197;Kålrot;Frukt & Grönt\n" +
                "198;Körsbär;Fryst\n" +
                "199;Körsbärstomater;Frukt & Grönt\n" +
                "200;Kött;Kött & Chark\n" +
                "201;Köttbullar;Fryst\n" +
                "202;Köttfärs;Kött & Chark\n" +
                "203;Lagerblad;Kryddor & Olja\n" +
                "204;Laktosfri yoghurt;Mejeri\n" +
                "205;Lasagneplattor;Torrvaror\n" +
                "206;Lax;Fiskbilen\n" +
                "207;Lemoncurd;Kryddor & Olja\n" +
                "208;Lemondricka;Mejeri\n" +
                "209;Leverpastej;Kött & Chark\n" +
                "210;Lime;Frukt & Grönt\n" +
                "211;Limpa;Bröd\n" +
                "212;Lingongrova;Bröd\n" +
                "213;Lingonsylt;Kryddor & Olja\n" +
                "214;Linser;Torrvaror\n" +
                "215;Ljus;Torrvaror\n" +
                "216;Lärpa;Läsk & Apmat\n" +
                "217;Läsk;Läsk & Apmat\n" +
                "218;Lök;Frukt & Grönt\n" +
                "219;Lönnsirap;Torrvaror\n" +
                "220;Lövbiff;Kött & Chark\n" +
                "221;M&M;Godis & Kassan\n" +
                "222;Majonnäs;Torrvaror\n" +
                "223;Majs;Torrvaror\n" +
                "224;Mandariner;Frukt & Grönt\n" +
                "225;Mandel;Torrvaror\n" +
                "226;Mandelflarn;Torrvaror\n" +
                "227;Mandelmjöl;Torrvaror\n" +
                "228;Mandelmjölk;Mejeri\n" +
                "229;Mandelspån;Torrvaror\n" +
                "230;Mango Chutney;Kryddor & Olja\n" +
                "231;Mango;Frukt & Grönt\n" +
                "232;Margarin;Mejeri\n" +
                "233;Maskindisk;Hygien\n" +
                "234;Matlagningsyoghurt;Mejeri\n" +
                "235;Mini-Marsmallows;Godis & Kassan\n" +
                "236;Mini-baguetter;Bröd\n" +
                "237;Minimozzarella;Ost\n" +
                "238;Mjöl;Torrvaror\n" +
                "239;Mjölk;Mejeri\n" +
                "240;Morot;Frukt & Grönt\n" +
                "241;Morötter;Frukt & Grönt\n" +
                "242;Mozarella;Ost\n" +
                "243;Mozzarella;Ost\n" +
                "244;Muggpapper;Hygien\n" +
                "245;Muggpappet;Hygien\n" +
                "246;Musli;Torrvaror\n" +
                "247;Nachochips;Kryddor & Olja\n" +
                "248;Nachos;Kryddor & Olja\n" +
                "249;Naturgodis;Godis & Kassan\n" +
                "250;Nektariner;Frukt & Grönt\n" +
                "251;Never Stop;Godis & Kassan\n" +
                "252;Nudlar;Torrvaror\n" +
                "253;Nutella;Torrvaror\n" +
                "254;Nyponsoppa (färdig);Torrvaror\n" +
                "255;O'boy;Ost\n" +
                "256;Oboj;Kryddor & Olja\n" +
                "257;Oliver;Torrvaror\n" +
                "258;Olivkräm;Torrvaror\n" +
                "259;Olivolja;Kryddor & Olja\n" +
                "260;Olja;Kryddor & Olja\n" +
                "261;Oregano;Kryddor & Olja\n" +
                "262;Ost;Ost\n" +
                "263;Ostronsås;Kryddor & Olja\n" +
                "264;Oumph \"The pure filet\";Fryst\n" +
                "265;Oxfilet;Kött & Chark\n" +
                "266;Pak Choi;Frukt & Grönt\n" +
                "267;Palsternacka;Frukt & Grönt\n" +
                "268;Panko;Torrvaror\n" +
                "269;Pappasill;Fiskbilen\n" +
                "270;Paprika;Frukt & Grönt\n" +
                "271;Parmesan;Ost\n" +
                "272;Partyis;Fryst\n" +
                "273;Passionsfrukt;Frukt & Grönt\n" +
                "274;Pasta;Torrvaror\n" +
                "275;Pastaplattor;Torrvaror\n" +
                "276;Pauluns Supergröt;Torrvaror\n" +
                "277;Pecannötter;Läsk & Apmat\n" +
                "278;Pepparkakshus;Bröd\n" +
                "279;Persilja - fryst;Fryst\n" +
                "280;Persilja;Frukt & Grönt\n" +
                "281;Pesto;Torrvaror\n" +
                "282;Pinjenötter;Frukt & Grönt\n" +
                "283;Pitabröd;Bröd\n" +
                "284;Pizza;Ost\n" +
                "285;Pizzadeg;Ost\n" +
                "286;Plastburkar;Torrvaror\n" +
                "287;Plastpåsar;Torrvaror\n" +
                "288;Plättar;Ost\n" +
                "289;Polarbröd;Bröd\n" +
                "290;Polenta;Torrvaror\n" +
                "291;Pommac;Läsk & Apmat\n" +
                "292;Pommes frites;Fryst\n" +
                "293;Pompe;Fryst\n" +
                "294;Popcorn;Godis & Kassan\n" +
                "295;Poppy seeds;Läsk & Apmat\n" +
                "296;Portabello;Frukt & Grönt\n" +
                "297;Potatis kok;Frukt & Grönt\n" +
                "298;Potatis;Frukt & Grönt\n" +
                "299;Potatisgratäng;Kött & Chark\n" +
                "300;Potatismjöl;Torrvaror\n" +
                "301;Prinskorv;Kött & Chark\n" +
                "302;Proviva;Mejeri\n" +
                "303;Pulled pork;Kött & Chark\n" +
                "304;Pumpernickel;Bröd\n" +
                "305;Purjolök;Frukt & Grönt\n" +
                "306;Pytt i panna;Fryst\n" +
                "307;Päron;Frukt & Grönt\n" +
                "308;Pågenlimpa;Bröd\n" +
                "309;Quorn;Fryst\n" +
                "310;Quornfärs;Fryst\n" +
                "311;Raggmunk;Torrvaror\n" +
                "312;Rapsolja;Kryddor & Olja\n" +
                "313;Remouladsås;Fiskbilen\n" +
                "314;Revbensspjäll;Kött & Chark\n" +
                "315;Ricotta;Ost\n" +
                "316;Ris;Torrvaror\n" +
                "317;Risgrynsgröt;Ost\n" +
                "318;Risifrutti;Mejeri\n" +
                "319;Riskakor;Bröd\n" +
                "320;Risoni;Torrvaror\n" +
                "321;Riven ost;Ost\n" +
                "322;Romansallad;Frukt & Grönt\n" +
                "323;Romsås;Fiskbilen\n" +
                "324;Rostad lök;Kryddor & Olja\n" +
                "325;Rostbröd;Bröd\n" +
                "326;Rosépeppar;Kryddor & Olja\n" +
                "327;Rotselleri;Frukt & Grönt\n" +
                "328;Ruccola;Frukt & Grönt\n" +
                "329;Russin;Frukt & Grönt\n" +
                "330;Rädisor;Frukt & Grönt\n" +
                "331;Räkor;Fiskbilen\n" +
                "332;Rågbröd;Bröd\n" +
                "333;Rågsikt;Torrvaror\n" +
                "334;Rårörda lingon;Kryddor & Olja\n" +
                "335;Råsocker;Torrvaror\n" +
                "336;Röd lök;Frukt & Grönt\n" +
                "337;Röda linser - kokta;Torrvaror\n" +
                "338;Röda linser;Torrvaror\n" +
                "339;Rödbetor;Frukt & Grönt\n" +
                "340;Rödlök;Frukt & Grönt\n" +
                "341;Rödspätta;Fiskbilen\n" +
                "342;Rödvinssås;Kött & Chark\n" +
                "343;Saft;Kryddor & Olja\n" +
                "344;Sallad;Frukt & Grönt\n" +
                "345;Salladslök;Frukt & Grönt\n" +
                "346;Salladsmix;Frukt & Grönt\n" +
                "347;Salsa;Kryddor & Olja\n" +
                "348;Salt;Kryddor & Olja\n" +
                "349;Salta pinnar;Läsk & Apmat\n" +
                "350;Sambal Oelek;Kryddor & Olja\n" +
                "351;Schalottenlök;Frukt & Grönt\n" +
                "352;Schnitzlar;Fryst\n" +
                "353;Selleri;Frukt & Grönt\n" +
                "354;Senap;Kryddor & Olja\n" +
                "355;Sesamfrön;Kryddor & Olja\n" +
                "356;Sesamolja;Kryddor & Olja\n" +
                "357;Skinka;Kött & Chark\n" +
                "358;Skittles;Kryddor & Olja\n" +
                "359;Skogssvamp;Frukt & Grönt\n" +
                "360;Skorpor, kardemumma;Bröd\n" +
                "361;Skumtomtar;Godis & Kassan\n" +
                "362;Småtomater;Frukt & Grönt\n" +
                "363;Smör;Mejeri\n" +
                "364;Smördeg;Ost\n" +
                "365;Smörgåsgurka;Torrvaror\n" +
                "366;Snacks;Läsk & Apmat\n" +
                "367;Snapple;Läsk & Apmat\n" +
                "368;Snytpapper;Hygien\n" +
                "369;Socker;Torrvaror\n" +
                "370;Sockerärter;Frukt & Grönt\n" +
                "371;Soja;Kryddor & Olja\n" +
                "372;Sojabönor;Fryst\n" +
                "373;Sojamjölk;Mejeri\n" +
                "374;Solroskärnor;Torrvaror\n" +
                "375;Soltorkade tomater;Torrvaror\n" +
                "376;Sombreros;Läsk & Apmat\n" +
                "377;Sour cream;Läsk & Apmat\n" +
                "378;Soyagrädde;Mejeri\n" +
                "379;Soyghurt;Mejeri\n" +
                "380;Soygurt;Mejeri\n" +
                "381;Spagetti;Torrvaror\n" +
                "382;Spaghetti;Torrvaror\n" +
                "383;Sparris;Frukt & Grönt\n" +
                "384;Special-K;Torrvaror\n" +
                "385;Spenat;Frukt & Grönt\n" +
                "386;Spetskål;Frukt & Grönt\n" +
                "387;Sprutgrädde;Mejeri\n" +
                "388;St Agur;Ost\n" +
                "389;Stekfläsk;Kött & Chark\n" +
                "390;Stora vita bönor;Torrvaror\n" +
                "391;Strösocker;Torrvaror\n" +
                "392;Sugarnaps;Frukt & Grönt\n" +
                "393;Sugarsnaps;Frukt & Grönt\n" +
                "394;Sur Rut;Bröd\n" +
                "395;Surisar;Godis & Kassan\n" +
                "396;Svamp;Frukt & Grönt\n" +
                "397;Svampfond;Kryddor & Olja\n" +
                "398;Svarta bönor;Torrvaror\n" +
                "399;Svartpeppar;Kryddor & Olja\n" +
                "400;Svinto;Hygien\n" +
                "401;Sweet chili;Kryddor & Olja\n" +
                "402;Sweet'n Sour;Kryddor & Olja\n" +
                "403;Sylt;Kryddor & Olja\n" +
                "404;Syltad lök;Frukt & Grönt\n" +
                "405;Såpa;Hygien\n" +
                "406;Sötpotatis;Frukt & Grönt\n" +
                "407;Taco shells;Kryddor & Olja\n" +
                "408;Tacokrydda;Kryddor & Olja\n" +
                "409;Tacos;Kryddor & Olja\n" +
                "410;Tacoskal;Kryddor & Olja\n" +
                "411;Tagliatelle;Torrvaror\n" +
                "412;Tahini;Kryddor & Olja\n" +
                "413;Tandkräm;Hygien\n" +
                "414;Tapenade;Torrvaror\n" +
                "415;Tejp;Hygien\n" +
                "416;Tempeh;Happy Vegan\n" +
                "417;Teriyakisås;Kryddor & Olja\n" +
                "418;Timjan;Frukt & Grönt\n" +
                "419;Toalettpapper;Hygien\n" +
                "420;Tofu;Ost\n" +
                "421;Tomater;Frukt & Grönt\n" +
                "422;Tomatpurré;Kryddor & Olja\n" +
                "423;Tomteskum;Godis & Kassan\n" +
                "424;Tonic;Läsk & Apmat\n" +
                "425;Torky;Hygien\n" +
                "426;Torsk;Fiskbilen\n" +
                "427;Tortillabröd;Kryddor & Olja\n" +
                "428;Tortillas;Kryddor & Olja\n" +
                "429;Tranbär;Läsk & Apmat\n" +
                "430;Tuc;Bröd\n" +
                "431;Tuggummi;Godis & Kassan\n" +
                "432;Turkisk yoghurt;Mejeri\n" +
                "433;Tvättmedel - kulör;Hygien\n" +
                "434;Tvättmedel - vit;Hygien\n" +
                "435;Tvål;Hygien\n" +
                "436;Tändstickor;Godis & Kassan\n" +
                "437;Tårtbotten;Bröd\n" +
                "438;Valnötter;Frukt & Grönt\n" +
                "439;Vaniljglass;Fryst\n" +
                "440;Vaniljstång;Kryddor & Olja\n" +
                "441;Vaniljsås;Mejeri\n" +
                "442;Vaniljyoghurt;Mejeri\n" +
                "443;Vansinnespompe;Fryst\n" +
                "444;Vatten;Läsk & Apmat\n" +
                "445;Vattenkastanjer;Kryddor & Olja\n" +
                "446;Vattenmelon;Frukt & Grönt\n" +
                "447;Vegoburgare;Fryst\n" +
                "448;Vegofärs;Fryst\n" +
                "449;Vegokorv;Kött & Chark\n" +
                "450;Vetemjöl;Torrvaror\n" +
                "451;Vindruvor;Frukt & Grönt\n" +
                "452;Vispgrädde;Mejeri\n" +
                "453;Vit choklad;Godis & Kassan\n" +
                "454;Vita bönor;Torrvaror\n" +
                "455;Vitkål;Frukt & Grönt\n" +
                "456;Vitlök;Frukt & Grönt\n" +
                "457;Vitlöksbröd;Kryddor & Olja\n" +
                "458;Vitlökssås;Kött & Chark\n" +
                "459;Vitvinsvinäger;Kryddor & Olja\n" +
                "460;Västerbotten - riven;Ost\n" +
                "461;Västerbottenost;Ost\n" +
                "462;Växtgrädde;Mejeri\n" +
                "463;WC-rent;Hygien\n" +
                "464;Wasa Havre;Bröd\n" +
                "465;Wasa Husman;Bröd\n" +
                "466;Wasa Sport;Bröd\n" +
                "467;Wokgrönsaker;Fryst\n" +
                "468;Yes;Hygien\n" +
                "469;Yoghurt;Mejeri\n" +
                "470;Yogurt;Mejeri\n" +
                "471;Zucchini;Frukt & Grönt\n" +
                "472;Zyxx;Godis & Kassan\n" +
                "473;Ägg;Mejeri\n" +
                "474;Äggnudlar;Kryddor & Olja\n" +
                "475;Äppelcidervinäger;Kryddor & Olja\n" +
                "476;Äpplen;Frukt & Grönt\n" +
                "477;Ärtor majs paprika;Fryst\n" +
                "478;Ättika;Kryddor & Olja\n" +
                "479;Öl;Läsk & Apmat\n" +
                "480;Örtsalt;Kryddor & Olja\n";
    }
}
