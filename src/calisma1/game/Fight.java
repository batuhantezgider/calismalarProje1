package calisma1.game;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.*;

public final class Fight {

    private static final Fighter enemy = new Fighter();
    private static final Fighter player = new Fighter();

    public static void oyunBaslat() {

        Scanner scInt = new Scanner(System.in);

        int islemMenu;

        chanceName("Ismin Nedir Dovuscu ? ", player);

        enemy.nickname = "Enemy";

        do {

            islemMenu  = startMenu();

            switch (islemMenu) {

                case 1 -> {

                    int islemZorluk;

                    do {

                        islemZorluk = dificultSwitch();

                        switch (islemZorluk) {

                            case 1 -> {
                                enemy.health = 100;
                                enemy.attack = 8;
                                enemy.manaCapacity = 100;

                                fight(50, Fighter.Dificult.EASY);

                            }

                            case 2 -> {
                                enemy.health = 100;
                                enemy.attack = 15;
                                enemy.manaCapacity = 100;

                                fight(100, Fighter.Dificult.NORMAL);

                            }

                            case 3 -> {
                                enemy.health = 150;
                                enemy.attack = 20;
                                enemy.manaCapacity = 150;

                                fight(200, Fighter.Dificult.HARD);

                            }

                            case 4 -> {
                                enemy.health = 500;
                                enemy.attack = 30;
                                enemy.manaCapacity = 200;

                                fight(500, Fighter.Dificult.VERY_HARD);

                            }

                            default -> {
                                System.out.println("Bu islem listede yok");
                                sleep(1000);
                            }

                        }

                    } while (islemZorluk < 0 || islemZorluk > 4);

                    System.out.println("\nOyun Basliyor...\n");

                }

                case 2 -> {
                    System.out.println("""
                            Oyun Hakkinda :
                            Oyunda karsinizda bir rakip var.
                            Rakibi elinizdeki seceneklerle yenmeye calisiyorsunuz.
                            
                            Nasil Oynanir ?
                            Oyunda karsiniza bir liste cikacak.
                            Oradaki numarali yazip o secenekleri kullanirsiniz.
                            Oyunu listedeki numaralarla (1 2 3 vb.) kontrol edersiniz, mesela "1 ates topu at" gibi.
                            Oyun Bir Rastgele Sayi Tutacak, siz o sayiya  ne kadar yaklasirsaniz o kadar isabetli vurursunuz.
                            
                            """);
                    sleep(5000);
                }

                case 3 -> {

                    int islemMarket;

                    do {

                        islemMarket = marketMenu();

                        switch (islemMarket){

                            case 1 -> {

                                System.out.println("Silahlar" +
                                        "\n Senin Paran = " + player.cash +
                                        "\n1 - Normal Kilic (50)    " + Fighter.Weapons.NORMAL_SWORD.getOwned() +
                                        "\n2 - Agir Kilic (200) " + Fighter.Weapons.HEAVY_SWORD.getOwned() +
                                        "\n3 - Karanligin Bicaklari (500)   " + Fighter.Weapons.DARK_OF_BLADES.getOwned() +
                                        "\n0 - Geri");

                                int indexWeapon = scInt.nextInt();
                                if (indexWeapon < Fighter.Weapons.values().length - 1 && indexWeapon > 0){
                                    int cost = Fighter.Weapons.values()[indexWeapon].getCost();
                                    if (player.cash >= cost && !player.weapons.contains(Fighter.Weapons.values()[indexWeapon])) {
                                        System.out.println("Satin Alma Islemi Tamamlandi");
                                        player.cash -= cost;
                                        player.weapons.add(Fighter.Weapons.values()[indexWeapon]);
                                        Fighter.Weapons.values()[indexWeapon].setOwned(true);
                                    } else {
                                        System.out.println("Gecersiz Islem !");
                                    }
                                } else {
                                    System.out.println("Secilen Islem listede yok !");
                                }

                                /*
                                switch (islemMarket_Silahlar){

                                    case 1 -> {

                                        if (player.cash >= Fighter.Weapons.NORMAL_SWORD.getCost() && !player.weapons.equals(Fighter.Weapons.NORMAL_SWORD)){
                                            System.out.println("Satin Alma Islemi Tamamlandi");
                                            player.cash -= Fighter.Weapons.NORMAL_SWORD.getCost();
                                        }
                                        else {
                                            System.out.println("Yetersiz Para");
                                        }

                                    }

                                    case 2 -> {

                                        if (player.cash >= Fighter.Weapons.HEAVY_SWORD.getCost() && !player.weapons.equals(Fighter.Weapons.HEAVY_SWORD)){
                                            System.out.println("Satin Alma Islemi Tamamlandi");
                                            player.cash -= Fighter.Weapons.HEAVY_SWORD.getCost();
                                        }
                                        else {
                                            System.out.println("Yetersiz Para");
                                        }

                                    }

                                    case 3 -> {
                                        if (player.cash >= Fighter.Weapons.DARK_OF_BLADES.ordinal() && !player.weapons.equals(Fighter.Weapons.HEAVY_SWORD)){
                                            System.out.println("Satin Alma Islemi Tamamlandi");
                                            player.cash -= Fighter.Weapons.HEAVY_SWORD.getCost();
                                        }
                                        else {
                                            System.out.println("Yetersiz Para");
                                        }

                                    }

                                    case 0 -> {

                                    }

                                }
                                */

                            }

                            case 2 -> {

                                System.out.println("Zirhlar" +
                                        "\n Senin Paran = " + player.cash +
                                        "\n1 - Normal Zirh (150)    " + Fighter.Armors.NORMAL_ARMOR.getOwned() +
                                        "\n2 - Agir Zirh (300)  " + Fighter.Armors.HEAVY_ARMOR.getOwned() +
                                        "\n3 - Karanligin Zirhi (500)   " + Fighter.Armors.DARK_OF_ARMOR.getOwned() +
                                        "\n0 - Geri");

                                int indexWeapon = scInt.nextInt();
                                if (indexWeapon < Fighter.Armors.values().length - 1 && indexWeapon > 0){
                                    int cost = Fighter.Armors.values()[indexWeapon].getCost();
                                    if (player.cash >= cost && !player.armors.contains(Fighter.Armors.values()[indexWeapon])) {
                                        System.out.println("Satin Alma Islemi Tamamlandi");
                                        player.cash -= cost;
                                        player.armors.add(Fighter.Armors.values()[indexWeapon]);
                                        Fighter.Armors.values()[indexWeapon].setOwned(true);
                                    } else System.out.println("Gecersiz Islem ! ");
                                }
                                else {
                                    System.out.println("Secilen Islem listede yok !");
                                }

                            }

                            case 3 -> {

                                System.out.println("Buyuler" +
                                        "\n Senin Paran = " + player.cash +
                                        "\n1 - Super Yumruk (500)   " + Fighter.Magics.SUPER_PUNCH.getOwned() +
                                        "\n2 - Ates Topu (1000) " + Fighter.Magics.FIREBALL.getOwned() +
                                        "\n3 - Karanligin Atesi (3000)  " + Fighter.Magics.DARK_OF_FIRE.getOwned() +
                                        "\n0 - Geri");

                                int indexWeapon = scInt.nextInt();
                                if (indexWeapon < Fighter.Magics.values().length - 1 && indexWeapon >= 0){
                                    int cost = Fighter.Magics.values()[indexWeapon].getCost();
                                    if (player.cash >= cost && !player.magics.contains(Fighter.Magics.values()[indexWeapon])) {
                                        System.out.println("Satin Alma Islemi Tamamlandi");
                                        player.cash -= cost;
                                        player.magics.add(Fighter.Magics.values()[indexWeapon]);
                                        Fighter.Magics.values()[indexWeapon].setOwned(true);
                                    } else System.out.println("Gecersiz Islem ! ");
                                }
                                else {
                                    System.out.println("Secilen Islem listede yok !");
                                }

                            }

                            case 0 -> {

                            }

                            default -> {

                            }

                        }

                    }while (islemMarket != 0);

                }

                case 4 -> {

                    int islemInventory;

                     do {

                         islemInventory = inventoryMenu();

                         switch (islemInventory){

                             case 1 -> {

                                 for (int i = 0; i < player.weapons.size(); i++) {
                                         System.out.println(i + 1 + ". " + player.weapons.get(i) + "   " + player.weapons.get(i).equals(player.weapon));
                                 }



                             }

                             case 2 -> {

                             }

                             case 3 -> {

                             }

                             case 0 -> {

                             }

                             default -> {

                             }



                         }

                     }while (islemInventory < 3 && islemInventory > 0);

                }

                case 0 -> {
                    System.out.println("Cikis Yapiliyor...");
                    sleep(1000);
                }

                default -> {
                    System.out.println("Bu secim listede yok");
                    sleep(1000);
                }

            }

        } while (islemMenu != 0);

    }

    private static int startMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                ! Hosgeldin !
                Secmek icin listedeki numaralardan birini yazin.
                1 - Basla
                2 - Oyun Hakkinda
                3 - Market
                4 - Envanter
                0 - Cikis""");
        return sc.nextInt();
    }

    private static int dificultSwitch() {
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                Zorluk Sec :
                1 - Kolay
                2 - Normal
                3 - Zor
                4 - Cok Zor""");
        return sc.nextInt();
    }

    private static int marketMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                MARKET
                1 - Silahlar
                2 - Zirhlar
                3 - Buyuler
                0 - Cikis
                """);
        return sc.nextInt();
    }

    private static int inventoryMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("""
                ENVANTER
                1 - Silahlar
                2 - Zirhlar
                3 - Buyuler
                0 - Cikis
                """);
        return sc.nextInt();
    }

    private static void fight(int cash, Fighter.Dificult dificult){

        switch (dificult){
            case EASY -> {

            }
            case NORMAL -> {

            }
            case HARD -> {

            }
            case VERY_HARD -> {

            }
        }

        do {

            if (player.health <= 0){
                player.isAlive = false;
            }
            if (enemy.health <= 0){
                enemy.isAlive = false;
            }

        }while (player.isAlive && enemy.isAlive);

        if (player.isAlive){
            System.out.println("Kazandin ! +" + cash);
            player.cash += cash;
        }
        if (enemy.isAlive){
            System.out.println("Kaybettin ! -" + (cash/4));
            player.cash -= (int)(cash / 4);
        }

        player.isAlive = true;
        enemy.isAlive = true;

    }



    private static void chanceName(String text, Fighter fighter) {
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        fighter.nickname = sc.nextLine();
    }

    private static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void activeManaSystem(){

    }

    private static class Fighter {

        private String nickname = "fighter";
        private int health = 100;
        private int attack = 15;
        private int mana = 0;
        private int manaCapacity = 100;
        private Weapons weapon = Weapons.NONE;
        private Armors armor = Armors.NONE;
        private final Magics magic = Magics.NONE;
        private boolean isAlive = true;
        private int cash = 100;

        private List<Weapons> weapons = new ArrayList<>(Arrays.asList(Weapons.NONE));
        private List<Armors> armors = new ArrayList<>(Arrays.asList(Armors.NONE));
        private List<Magics> magics = new ArrayList<>(Arrays.asList(Magics.NONE));

        private List<String> nicknames = new ArrayList<>();

        private static final List<String> forbiddenNames = new ArrayList<>(List.of("hasan", "shadow", "fighter", "mal", "patates", "", ""));

        private void setNickname(String name) throws RuntimeException{
            if (nicknames.contains(name.toLowerCase())){
                throw new RuntimeException("Ayni Isimli 2 Oyuncu olamaz");
            }
            else if (forbiddenNames.contains(name.toLowerCase())){
                throw new RuntimeException("Bu Isim Gecersiz");
            }
            else {
                this.nickname = name;
                nicknames.add(name);
            }
        }

        private int attack(){
            return 0;
        }

        private enum Weapons {
            NONE(0, 0, true),
            NORMAL_SWORD(50, 10, false),
            HEAVY_SWORD(200, 20, false),
            DARK_OF_BLADES(500, 30, false),
            ;

            private final int cost;
            private final int damage;
            private boolean isOwned;

            Weapons(int cost, int damage, boolean isOwned){
                this.cost = cost;
                this.damage = damage;
                this.isOwned = isOwned;
            }

            public boolean getOwned(){
                return this.isOwned;
            }

            public void setOwned(boolean bolean){
                this.isOwned = bolean;
            }

            public int getCost(){
                return this.cost;
            }

            public int getDamage(){
                return this.damage;
            }

        }

        private enum Armors {
            NONE(0, 0, true),
            NORMAL_ARMOR(150, 10, false),
            HEAVY_ARMOR(300, 30, false),
            DARK_OF_ARMOR(500, 50, false),
            ;

            private final int cost;
            private final int protection;
            private boolean isOwned;

            Armors(int cost, int protection, boolean isOwned){
                this.cost = cost;
                this.protection = protection;
                this.isOwned = isOwned;
            }

            public boolean getOwned(){
                return this.isOwned;
            }

            public void setOwned(boolean bolean){
                this.isOwned = bolean;
            }

            public int getCost(){
                return this.cost;
            }

            public int getProtection(){
                return this.protection;
            }

        }

        private enum Magics {
            NONE(0, 0, true),
            SUPER_PUNCH(500, 20, false),
            FIREBALL(1000, 30, false),
            DARK_OF_FIRE(3000, 50, false),
            ;

            private final int cost;
            private final int damage;
            private boolean isOwned;

            Magics(int cost, int damage, boolean isOwned){
                this.cost = cost;
                this.damage = damage;
                this.isOwned = isOwned;
            }

            public boolean getOwned(){
                return this.isOwned;
            }

            public void setOwned(boolean bolean){
                this.isOwned = bolean;
            }

            public int getCost(){
                return this.cost;
            }

            public int getDamage(){
                return this.damage;
            }

        }

        private enum Dificult {
            EASY,
            NORMAL,
            HARD,
            VERY_HARD,
            ;
        }

    }



}
