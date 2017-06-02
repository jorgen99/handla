package solidbeans.com.handla.db;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class MockDB {

    public static final String TAG = MockDB.class.getSimpleName();

    public static List<Category> constructCategories(CategoryDao categoryDao) {
        String cs = catetoryString();
        List<Category> categories = createCategoryList(cs);
        for (Category category : categories) {
            Category unique = categoryDao.queryBuilder()
                    .where(CategoryDao.Properties.Name.eq(category.getName()))
                    .build().unique();
            if(unique == null) {
                categoryDao.save(category);
            }

        }
        return categories;
    }

    public static List<ItemType> constructItemTypes(List<Category> categories, ItemTypeDao itemTypeDao) {
        String its = itemTypeString();
        List<ItemType> itemTypes = createItemTypeList(categories, its);
        for (ItemType itemType : itemTypes) {
            ItemType unique = itemTypeDao.queryBuilder()
                    .where(ItemTypeDao.Properties.Name.eq(itemType.getName()))
                    .build().unique();
            if(unique == null) {
                itemTypeDao.save(itemType);
            }
        }
        return itemTypes;
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
        String name = parts[0];
        String cName = parts[1];
        Category c = findCategory(categories, cName);
//        Log.d(TAG, "createItemType: category :" + c);
        return new ItemType(name, c);
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
                "14;Godis & Kassan;#86D0A2";
    }

    private static String itemTypeString() {
        return "9V batteri;Godis & Kassan\n" +
                "Agavesirap;Torrvaror\n" +
                "Aluminiumfolie;Torrvaror\n" +
                "Amee;Läsk & Apmat\n" +
                "Ananas;Frukt & Grönt\n" +
                "Ancho;Rollands\n" +
                "Annanasringar;Kryddor & Olja\n" +
                "Apelsiner;Frukt & Grönt\n" +
                "Apelsinjuice;Mejeri\n" +
                "Apmat;Läsk & Apmat\n" +
                "Aubergine;Frukt & Grönt\n" +
                "Avokado - fryst;Fryst\n" +
                "Avokado;Frukt & Grönt\n" +
                "Avorioris;Torrvaror\n" +
                "Babyspenat;Frukt & Grönt\n" +
                "Bacon;Kött & Chark\n" +
                "Badrumsspray;Hygien\n" +
                "Bake-off bröd;Bröd\n" +
                "Baked beans;Torrvaror\n" +
                "Bakplåtspapper;Torrvaror\n" +
                "Bakpotatis;Frukt & Grönt\n" +
                "Balsamvinäger;Rollands\n" +
                "Bambuskott;Torrvaror\n" +
                "Bananer;Frukt & Grönt\n" +
                "Bars;Godis & Kassan\n" +
                "Basilika;Frukt & Grönt\n" +
                "Basmatiris;Torrvaror\n" +
                "Bea;Kött & Chark\n" +
                "Ben & Jerry;Godis & Kassan\n" +
                "Blomkål;Frukt & Grönt\n" +
                "Blåbär;Fryst\n" +
                "Bondbröd;Bröd\n" +
                "Bregott;Mejeri\n" +
                "Broccoli;Frukt & Grönt\n" +
                "Brysselkål;Frukt & Grönt\n" +
                "Bröd;Bröd\n" +
                "Bubbelbad;Hygien\n" +
                "Bulgur;Torrvaror\n" +
                "Buljong;Kryddor & Olja\n" +
                "Bulle;Bröd\n" +
                "Böngroddar;Frukt & Grönt\n" +
                "Bönor;Torrvaror\n" +
                "Cashewnötter;Frukt & Grönt\n" +
                "Champinjoner;Frukt & Grönt\n" +
                "Chevre;Ost\n" +
                "Chiabatta;Bröd\n" +
                "Chibba;Läsk & Apmat\n" +
                "Chili;Frukt & Grönt\n" +
                "Chilipulver;Kryddor & Olja\n" +
                "Chilisås;Kryddor & Olja\n" +
                "Chipotle;Rollands\n" +
                "Chips;Läsk & Apmat\n" +
                "Choklad;Godis & Kassan\n" +
                "Chokladkaka;Godis & Kassan\n" +
                "Ciabatta;Bröd\n" +
                "Citron;Frukt & Grönt\n" +
                "Citrongräs;Frukt & Grönt\n" +
                "Citrusdryck;Mejeri\n" +
                "Cola zero;Läsk & Apmat\n" +
                "Cola;Läsk & Apmat\n" +
                "Cornichon;Torrvaror\n" +
                "Cous Cous;Torrvaror\n" +
                "Creme fraiche;Mejeri\n" +
                "Crostini;Bröd\n" +
                "Crustini;Bröd\n" +
                "Crème Fraîche;Mejeri\n" +
                "Cuornfärs;Fryst\n" +
                "Curry;Kryddor & Olja\n" +
                "Currypasta - grön;Kryddor & Olja\n" +
                "Currypasta - röd;Kryddor & Olja\n" +
                "Dadlar;Frukt & Grönt\n" +
                "Delocatoboll;Bröd\n" +
                "Digestive;Bröd\n" +
                "Dijonsenap;Kryddor & Olja\n" +
                "Dill;Bröd\n" +
                "Dipp;Läsk & Apmat\n" +
                "Dippkrydda;Kryddor & Olja\n" +
                "Diskborste;Hygien\n" +
                "Diskmedel;Hygien\n" +
                "Drickyoghurt;Mejeri\n" +
                "Drottningsylt;Kryddor & Olja\n" +
                "Dyra fröknäcke;Bröd\n" +
                "Edamamebönor;Fryst\n" +
                "Fajitas;Kryddor & Olja\n" +
                "Falafel;Fryst\n" +
                "Falukorv;Kött & Chark\n" +
                "Fanta Zero;Läsk & Apmat\n" +
                "Fanta;Läsk & Apmat\n" +
                "Feta;Ost\n" +
                "Fettuccine;Torrvaror\n" +
                "Fil;Mejeri\n" +
                "Filmjölk;Mejeri\n" +
                "Finn Crisp;Bröd\n" +
                "Flingor;Torrvaror\n" +
                "Fläsk;Kött & Chark\n" +
                "Fläskfilé;Kött & Chark\n" +
                "Folie;Torrvaror\n" +
                "Frukt;Frukt & Grönt\n" +
                "Fry's vegoschitzel;Fryst\n" +
                "Fröknäcke;Bröd\n" +
                "Fun light;Kryddor & Olja\n" +
                "Fänkål;Frukt & Grönt\n" +
                "Färsk pasta;Kött & Chark\n" +
                "Förrättsgrej;Fiskbilen\n" +
                "GB-sandwich;Fryst\n" +
                "Gainomax;Godis & Kassan\n" +
                "Garam masala;Kryddor & Olja\n" +
                "Gemsallad;Frukt & Grönt\n" +
                "Gene;Bröd\n" +
                "Getost;Ost\n" +
                "Gifflar;Bröd\n" +
                "Gladpac;Hygien\n" +
                "Glass;Fryst\n" +
                "Glödlampa;Hygien\n" +
                "Gnocchi;Torrvaror\n" +
                "Godis;Godis & Kassan\n" +
                "Godisrem;Godis & Kassan\n" +
                "Gravad lax;Fiskbilen\n" +
                "Grillkrydda;Kryddor & Olja\n" +
                "Grillsås;Kryddor & Olja\n" +
                "Groddar;Frukt & Grönt\n" +
                "Grädde;Mejeri\n" +
                "Gräddfil;Mejeri\n" +
                "Gräslök;Frukt & Grönt\n" +
                "Grönkål;Frukt & Grönt\n" +
                "Grönsaker;Frukt & Grönt\n" +
                "Grönsaksbuljong;Kryddor & Olja\n" +
                "Gröt;Kött & Chark\n" +
                "Gul lök;Frukt & Grönt\n" +
                "Gulash;Torrvaror\n" +
                "Gurka;Frukt & Grönt\n" +
                "Hallogenlampor;Hygien\n" +
                "Hallon - frysta;Fryst\n" +
                "Hallonsorbet;Fryst\n" +
                "Hallosylt;Kryddor & Olja\n" +
                "Halloumi;Ost\n" +
                "Hamburgare;Fryst\n" +
                "Hamburgerbröd;Bröd\n" +
                "Hamburgerdressing;Kryddor & Olja\n" +
                "Haricot verts;Frukt & Grönt\n" +
                "Hasselnötter;Torrvaror\n" +
                "Havregryn;Torrvaror\n" +
                "Hela tomater;Torrvaror\n" +
                "Hollandaise;Kött & Chark\n" +
                "Honung;Torrvaror\n" +
                "Hårdost;Ost\n" +
                "Indian Tonic;Läsk & Apmat\n" +
                "Ingefära;Frukt & Grönt\n" +
                "Jalapenos;Kryddor & Olja\n" +
                "Japansk soja;Kryddor & Olja\n" +
                "Jasminris;Torrvaror\n" +
                "Jordgubbar - frysta;Fryst\n" +
                "Jordgubbar;Fryst\n" +
                "Jordgubbssylt;Kryddor & Olja\n" +
                "Jordnötsolja;Kryddor & Olja\n" +
                "Jordnötssmör;Frukt & Grönt\n" +
                "Jordnötter;Godis & Kassan\n" +
                "Jordärtskockor;Frukt & Grönt\n" +
                "Jos;Mejeri\n" +
                "Julmust;Läsk & Apmat\n" +
                "Jäst;Mejeri\n" +
                "K-special;Torrvaror\n" +
                "Kaffe;Bröd\n" +
                "Kaffefilter;Kryddor & Olja\n" +
                "Kakao;Torrvaror\n" +
                "Kakor;Bröd\n" +
                "Kalamata;Torrvaror\n" +
                "Kalles kaviar;Mejeri\n" +
                "Kamutvete;Torrvaror\n" +
                "Kanel;Kryddor & Olja\n" +
                "Kanelgifflar;Bröd\n" +
                "Kantareller;Frukt & Grönt\n" +
                "Kaviar;Mejeri\n" +
                "Kebabkött;Kött & Chark\n" +
                "Keso;Mejeri\n" +
                "Ketchup;Kryddor & Olja\n" +
                "Kikärter;Torrvaror\n" +
                "Knäckebröd;Bröd\n" +
                "Knäckemix;Torrvaror\n" +
                "Kokosflingor;Torrvaror\n" +
                "Kokosmjölk;Torrvaror\n" +
                "Kokossocker;Torrvaror\n" +
                "Koriander;Frukt & Grönt\n" +
                "Korv;Kött & Chark\n" +
                "Korvbröd;Bröd\n" +
                "Kronärtskocka;Kryddor & Olja\n" +
                "Kronärtskockspesto;Torrvaror\n" +
                "Krossade tomater;Torrvaror\n" +
                "Krutonger;Bröd\n" +
                "Kräftstjärtar;Kött & Chark\n" +
                "Kvarg;Mejeri\n" +
                "Kyckling;Kött & Chark\n" +
                "Kycklingfilé;Kött & Chark\n" +
                "Kycklingklubba;Kött & Chark\n" +
                "Kycklingklubbor - frysta;Fryst\n" +
                "Kycklinglårfilé;Kött & Chark\n" +
                "Kålrot;Frukt & Grönt\n" +
                "Körsbär;Fryst\n" +
                "Körsbärstomater;Frukt & Grönt\n" +
                "Kött;Kött & Chark\n" +
                "Köttbullar;Fryst\n" +
                "Köttfärs;Kött & Chark\n" +
                "Lagerblad;Kryddor & Olja\n" +
                "Laktosfri yoghurt;Mejeri\n" +
                "Lasagneplattor;Torrvaror\n" +
                "Lax;Fiskbilen\n" +
                "Lemoncurd;Kryddor & Olja\n" +
                "Lemondricka;Mejeri\n" +
                "Leverpastej;Kött & Chark\n" +
                "Lime;Frukt & Grönt\n" +
                "Limpa;Bröd\n" +
                "Lingongrova;Bröd\n" +
                "Lingonsylt;Kryddor & Olja\n" +
                "Linser;Torrvaror\n" +
                "Ljus;Torrvaror\n" +
                "Lärpa;Läsk & Apmat\n" +
                "Läsk;Läsk & Apmat\n" +
                "Lök;Frukt & Grönt\n" +
                "Lönnsirap;Torrvaror\n" +
                "Lövbiff;Kött & Chark\n" +
                "M&M;Godis & Kassan\n" +
                "Majonnäs;Torrvaror\n" +
                "Majs;Torrvaror\n" +
                "Mandariner;Frukt & Grönt\n" +
                "Mandel;Torrvaror\n" +
                "Mandelflarn;Torrvaror\n" +
                "Mandelmjöl;Torrvaror\n" +
                "Mandelmjölk;Mejeri\n" +
                "Mandelspån;Torrvaror\n" +
                "Mango Chutney;Kryddor & Olja\n" +
                "Mango;Frukt & Grönt\n" +
                "Margarin;Mejeri\n" +
                "Maskindisk;Hygien\n" +
                "Matlagningsyoghurt;Mejeri\n" +
                "Mini-Marsmallows;Godis & Kassan\n" +
                "Mini-baguetter;Bröd\n" +
                "Minimozzarella;Ost\n" +
                "Mjöl;Torrvaror\n" +
                "Mjölk;Mejeri\n" +
                "Morot;Frukt & Grönt\n" +
                "Morötter;Frukt & Grönt\n" +
                "Mozarella;Ost\n" +
                "Mozzarella;Ost\n" +
                "Muggpapper;Hygien\n" +
                "Muggpappet;Hygien\n" +
                "Musli;Torrvaror\n" +
                "Nachochips;Kryddor & Olja\n" +
                "Nachos;Kryddor & Olja\n" +
                "Naturgodis;Godis & Kassan\n" +
                "Nektariner;Frukt & Grönt\n" +
                "Never Stop;Godis & Kassan\n" +
                "Nudlar;Torrvaror\n" +
                "Nutella;Torrvaror\n" +
                "Nyponsoppa (färdig);Torrvaror\n" +
                "O'boy;Ost\n" +
                "Oboj;Kryddor & Olja\n" +
                "Oliver;Torrvaror\n" +
                "Olivkräm;Torrvaror\n" +
                "Olivolja;Kryddor & Olja\n" +
                "Olja;Kryddor & Olja\n" +
                "Oregano;Kryddor & Olja\n" +
                "Ost;Ost\n" +
                "Ostronsås;Kryddor & Olja\n" +
                "Oumph \"The pure filet\";Fryst\n" +
                "Oxfilet;Kött & Chark\n" +
                "Pak Choi;Frukt & Grönt\n" +
                "Palsternacka;Frukt & Grönt\n" +
                "Panko;Torrvaror\n" +
                "Pappasill;Fiskbilen\n" +
                "Paprika;Frukt & Grönt\n" +
                "Parmesan;Ost\n" +
                "Partyis;Fryst\n" +
                "Passionsfrukt;Frukt & Grönt\n" +
                "Pasta;Torrvaror\n" +
                "Pastaplattor;Torrvaror\n" +
                "Pauluns Supergröt;Torrvaror\n" +
                "Pecannötter;Läsk & Apmat\n" +
                "Pepparkakshus;Bröd\n" +
                "Persilja - fryst;Fryst\n" +
                "Persilja;Frukt & Grönt\n" +
                "Pesto;Torrvaror\n" +
                "Pinjenötter;Frukt & Grönt\n" +
                "Pitabröd;Bröd\n" +
                "Pizza;Ost\n" +
                "Pizzadeg;Ost\n" +
                "Plastburkar;Torrvaror\n" +
                "Plastpåsar;Torrvaror\n" +
                "Plättar;Ost\n" +
                "Polarbröd;Bröd\n" +
                "Polenta;Torrvaror\n" +
                "Pommac;Läsk & Apmat\n" +
                "Pommes frites;Fryst\n" +
                "Pompe;Fryst\n" +
                "Popcorn;Godis & Kassan\n" +
                "Poppy seeds;Läsk & Apmat\n" +
                "Portabello;Frukt & Grönt\n" +
                "Potatis kok;Frukt & Grönt\n" +
                "Potatis;Frukt & Grönt\n" +
                "Potatisgratäng;Kött & Chark\n" +
                "Potatismjöl;Torrvaror\n" +
                "Prinskorv;Kött & Chark\n" +
                "Proviva;Mejeri\n" +
                "Pulled pork;Kött & Chark\n" +
                "Pumpernickel;Bröd\n" +
                "Purjolök;Frukt & Grönt\n" +
                "Pytt i panna;Fryst\n" +
                "Päron;Frukt & Grönt\n" +
                "Pågenlimpa;Bröd\n" +
                "Quorn;Fryst\n" +
                "Quornfärs;Fryst\n" +
                "Raggmunk;Torrvaror\n" +
                "Rapsolja;Kryddor & Olja\n" +
                "Remouladsås;Fiskbilen\n" +
                "Revbensspjäll;Kött & Chark\n" +
                "Ricotta;Ost\n" +
                "Ris;Torrvaror\n" +
                "Risgrynsgröt;Ost\n" +
                "Risifrutti;Mejeri\n" +
                "Riskakor;Bröd\n" +
                "Risoni;Torrvaror\n" +
                "Riven ost;Ost\n" +
                "Romansallad;Frukt & Grönt\n" +
                "Romsås;Fiskbilen\n" +
                "Rostad lök;Kryddor & Olja\n" +
                "Rostbröd;Bröd\n" +
                "Rosépeppar;Kryddor & Olja\n" +
                "Rotselleri;Frukt & Grönt\n" +
                "Ruccola;Frukt & Grönt\n" +
                "Russin;Frukt & Grönt\n" +
                "Rädisor;Frukt & Grönt\n" +
                "Räkor;Fiskbilen\n" +
                "Rågbröd;Bröd\n" +
                "Rågsikt;Torrvaror\n" +
                "Rårörda lingon;Kryddor & Olja\n" +
                "Råsocker;Torrvaror\n" +
                "Röd lök;Frukt & Grönt\n" +
                "Röda linser - kokta;Torrvaror\n" +
                "Röda linser;Torrvaror\n" +
                "Rödbetor;Frukt & Grönt\n" +
                "Rödlök;Frukt & Grönt\n" +
                "Rödspätta;Fiskbilen\n" +
                "Rödvinssås;Kött & Chark\n" +
                "Saft;Kryddor & Olja\n" +
                "Sallad;Frukt & Grönt\n" +
                "Salladslök;Frukt & Grönt\n" +
                "Salladsmix;Frukt & Grönt\n" +
                "Salsa;Kryddor & Olja\n" +
                "Salt;Kryddor & Olja\n" +
                "Salta pinnar;Läsk & Apmat\n" +
                "Sambal Oelek;Kryddor & Olja\n" +
                "Schalottenlök;Frukt & Grönt\n" +
                "Schnitzlar;Fryst\n" +
                "Selleri;Frukt & Grönt\n" +
                "Senap;Kryddor & Olja\n" +
                "Sesamfrön;Kryddor & Olja\n" +
                "Sesamolja;Kryddor & Olja\n" +
                "Skinka;Kött & Chark\n" +
                "Skittles;Kryddor & Olja\n" +
                "Skogssvamp;Frukt & Grönt\n" +
                "Skorpor, kardemumma;Bröd\n" +
                "Skumtomtar;Godis & Kassan\n" +
                "Småtomater;Frukt & Grönt\n" +
                "Smör;Mejeri\n" +
                "Smördeg;Ost\n" +
                "Smörgåsgurka;Torrvaror\n" +
                "Snacks;Läsk & Apmat\n" +
                "Snapple;Läsk & Apmat\n" +
                "Snytpapper;Hygien\n" +
                "Socker;Torrvaror\n" +
                "Sockerärter;Frukt & Grönt\n" +
                "Soja;Kryddor & Olja\n" +
                "Sojabönor;Fryst\n" +
                "Sojamjölk;Mejeri\n" +
                "Solroskärnor;Torrvaror\n" +
                "Soltorkade tomater;Torrvaror\n" +
                "Sombreros;Läsk & Apmat\n" +
                "Sour cream;Läsk & Apmat\n" +
                "Soyagrädde;Mejeri\n" +
                "Soyghurt;Mejeri\n" +
                "Soygurt;Mejeri\n" +
                "Spagetti;Torrvaror\n" +
                "Spaghetti;Torrvaror\n" +
                "Sparris;Frukt & Grönt\n" +
                "Special-K;Torrvaror\n" +
                "Spenat;Frukt & Grönt\n" +
                "Spetskål;Frukt & Grönt\n" +
                "Sprutgrädde;Mejeri\n" +
                "St Agur;Ost\n" +
                "Stekfläsk;Kött & Chark\n" +
                "Stora vita bönor;Torrvaror\n" +
                "Strösocker;Torrvaror\n" +
                "Sugarnaps;Frukt & Grönt\n" +
                "Sugarsnaps;Frukt & Grönt\n" +
                "Sur Rut;Bröd\n" +
                "Surisar;Godis & Kassan\n" +
                "Svamp;Frukt & Grönt\n" +
                "Svampfond;Kryddor & Olja\n" +
                "Svarta bönor;Torrvaror\n" +
                "Svartpeppar;Kryddor & Olja\n" +
                "Svinto;Hygien\n" +
                "Sweet chili;Kryddor & Olja\n" +
                "Sweet'n Sour;Kryddor & Olja\n" +
                "Sylt;Kryddor & Olja\n" +
                "Syltad lök;Frukt & Grönt\n" +
                "Såpa;Hygien\n" +
                "Sötpotatis;Frukt & Grönt\n" +
                "Taco shells;Kryddor & Olja\n" +
                "Tacokrydda;Kryddor & Olja\n" +
                "Tacos;Kryddor & Olja\n" +
                "Tacoskal;Kryddor & Olja\n" +
                "Tagliatelle;Torrvaror\n" +
                "Tahini;Kryddor & Olja\n" +
                "Tandkräm;Hygien\n" +
                "Tapenade;Torrvaror\n" +
                "Tejp;Hygien\n" +
                "Tempeh;Happy Vegan\n" +
                "Teriyakisås;Kryddor & Olja\n" +
                "Timjan;Frukt & Grönt\n" +
                "Toalettpapper;Hygien\n" +
                "Tofu;Ost\n" +
                "Tomater;Frukt & Grönt\n" +
                "Tomatpurré;Kryddor & Olja\n" +
                "Tomteskum;Godis & Kassan\n" +
                "Tonic;Läsk & Apmat\n" +
                "Torky;Hygien\n" +
                "Torsk;Fiskbilen\n" +
                "Tortillabröd;Kryddor & Olja\n" +
                "Tortillas;Kryddor & Olja\n" +
                "Tranbär;Läsk & Apmat\n" +
                "Tuc;Bröd\n" +
                "Tuggummi;Godis & Kassan\n" +
                "Turkisk yoghurt;Mejeri\n" +
                "Tvättmedel - kulör;Hygien\n" +
                "Tvättmedel - vit;Hygien\n" +
                "Tvål;Hygien\n" +
                "Tändstickor;Godis & Kassan\n" +
                "Tårtbotten;Bröd\n" +
                "Valnötter;Frukt & Grönt\n" +
                "Vaniljglass;Fryst\n" +
                "Vaniljstång;Kryddor & Olja\n" +
                "Vaniljsås;Mejeri\n" +
                "Vaniljyoghurt;Mejeri\n" +
                "Vansinnespompe;Fryst\n" +
                "Vatten;Läsk & Apmat\n" +
                "Vattenkastanjer;Kryddor & Olja\n" +
                "Vattenmelon;Frukt & Grönt\n" +
                "Vegoburgare;Fryst\n" +
                "Vegofärs;Fryst\n" +
                "Vegokorv;Kött & Chark\n" +
                "Vetemjöl;Torrvaror\n" +
                "Vindruvor;Frukt & Grönt\n" +
                "Vispgrädde;Mejeri\n" +
                "Vit choklad;Godis & Kassan\n" +
                "Vita bönor;Torrvaror\n" +
                "Vitkål;Frukt & Grönt\n" +
                "Vitlök;Frukt & Grönt\n" +
                "Vitlöksbröd;Kryddor & Olja\n" +
                "Vitlökssås;Kött & Chark\n" +
                "Vitvinsvinäger;Kryddor & Olja\n" +
                "Västerbotten - riven;Ost\n" +
                "Västerbottenost;Ost\n" +
                "Växtgrädde;Mejeri\n" +
                "WC-rent;Hygien\n" +
                "Wasa Havre;Bröd\n" +
                "Wasa Husman;Bröd\n" +
                "Wasa Sport;Bröd\n" +
                "Wokgrönsaker;Fryst\n" +
                "Yes;Hygien\n" +
                "Yoghurt;Mejeri\n" +
                "Yogurt;Mejeri\n" +
                "Zucchini;Frukt & Grönt\n" +
                "Zyxx;Godis & Kassan\n" +
                "Ägg;Mejeri\n" +
                "Äggnudlar;Kryddor & Olja\n" +
                "Äppelcidervinäger;Kryddor & Olja\n" +
                "Äpplen;Frukt & Grönt\n" +
                "Ärtor majs paprika;Fryst\n" +
                "Ättika;Kryddor & Olja\n" +
                "Öl;Läsk & Apmat\n" +
                "Örtsalt;Kryddor & Olja\n";
    }
}
