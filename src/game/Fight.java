package game;

import java.io.IOException;
import java.net.NoRouteToHostException;
import java.util.*;

public final class Fight {

    private static final Fighter enemy = new Fighter();
    private static final Fighter player = new Fighter();

    public static void oyunBaslat() {

        Scanner scInt = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        int islemMenu;

        enemy.setNickname("Enemy");

        System.out.print("Bir Isim Gir : ");
        String name = scStr.next();

        player.setNickname(name);

        player.isRealPlayer = true;

        do {

            islemMenu  = startMenu();

            switch (islemMenu) {

                case 1 -> {

                    int islemZorluk;

                    do {

                        islemZorluk = dificultSwitch();

                        switch (islemZorluk) {

                            case 1 -> {

                                fight(50, Fighter.Dificult.EASY);

                            }

                            case 2 -> {

                                fight(100, Fighter.Dificult.NORMAL);

                            }

                            case 3 -> {

                                fight(200, Fighter.Dificult.HARD);

                            }

                            case 4 -> {

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

                            case 0 -> {}

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

        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        switch (dificult){
            case EASY -> {
                enemy.health = 100;
                enemy.attack = 8;
                enemy.engagement = 15;
                enemy.weapon = Fighter.Weapons.NONE;
                enemy.armor = Fighter.Armors.NONE;
            }
            case NORMAL -> {
                enemy.health = 100;
                enemy.attack = 15;
                enemy.engagement = 10;
                enemy.weapon = Fighter.Weapons.NORMAL_SWORD;
                enemy.armor = Fighter.Armors.NORMAL_ARMOR;
            }
            case HARD -> {
                enemy.health = 100;
                enemy.attack = 18;
                enemy.engagement = 9;
                enemy.weapon = Fighter.Weapons.HEAVY_SWORD;
                enemy.armor = Fighter.Armors.HEAVY_ARMOR;
            }
            case VERY_HARD -> {
                enemy.health = 100;
                enemy.attack = 20;
                enemy.engagement = 8;
                enemy.weapon = Fighter.Weapons.DARK_OF_BLADES;
                enemy.armor = Fighter.Armors.DARK_OF_ARMOR;
            }
        }

        sleep(1000);
        System.out.println("0%");
        sleep(4000);
        System.out.println("25%");
        sleep(4000);
        System.out.println("50%");
        sleep(4000);
        System.out.println("75%");
        sleep(4000);
        System.out.println("100%");
        sleep(2000);
        System.out.println("Mac Basliyor...");
        sleep(5000);
        System.out.println("Karakterler Yukleniyor...");
        sleep(3000);
        System.out.println("MAC BASLADI");

        while (player.isAlive && enemy.isAlive){

            System.out.printf("Dovuscu %-5s,Can %s\n", player.nickname, player.health);
            System.out.printf("Dovuscu %-5s,Can %s\n", enemy.nickname, enemy.health);

            System.out.print("Sayi Tuttur (0 ile " + player.engagement + ") ");

            int playerAttackNum = sc.nextInt();

            player.attack(playerAttackNum, enemy);

            System.out.println("Dusmanin Sirasi...");

            sleep(1000);

            System.out.println("Dusman Vuruyor...");

            sleep(1000);

            int enemyAttackNum = random.nextInt(0, enemy.engagement);

            enemy.attack(enemyAttackNum, player);

            if (player.health <= 0){
                player.isAlive = false;
            }
            if (enemy.health <= 0){
                enemy.isAlive = false;
            }

        }

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
        private Magics magic = Magics.NONE;
        private boolean isAlive = true;
        private int cash = 100;
        private int engagement = 10;
        private boolean isRealPlayer = false;

        private List<Weapons> weapons = new ArrayList<>(Arrays.asList(Weapons.NONE));
        private List<Armors> armors = new ArrayList<>(Arrays.asList(Armors.NONE));
        private List<Magics> magics = new ArrayList<>(Arrays.asList(Magics.NONE));

        private List<String> nicknames = new ArrayList<>();

        private static final List<String> forbiddenNames = new ArrayList<>(List.of("hasan", "shadow", "fighter", "mal", "patates", "hiyar", ""));
        private static final Random random = new Random();

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

        private void attack(int num, Fighter enemy){

            int rand = random.nextInt(0,engagement + 1);

            int max = Math.max(num, rand);
            int min = Math.min(num, rand);

            int different = Math.abs(max - min);

            if (different < 0) {
                throw new RuntimeException("!!! Different Negativ bir sayi OLAMAZ !!!");
            }

            if (isRealPlayer) {
                if (different == 0) {
                    System.out.println("Tam Isabet !");
                    System.out.println("Verilen Hasar = " + (attack + weapon.damage));
                    enemy.health -= enemy.takeDamage(attack + weapon.damage);
                }
                else if (different > 0 && different < 3) {
                    System.out.println("Iyi Isabet !");
                    System.out.println("Verilen Hasar = " + (int) ((attack + weapon.damage) / 1.2));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 1.2));
                }
                else if (different >= 3 && different <= 5) {
                    System.out.println("Az Cok Vurdun Iste !");
                    System.out.println("Verilen Hasar = " + (int) ((attack + weapon.damage) / 1.5));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 1.5));
                }
                else if (different > 5 && different < 9) {
                    System.out.println("Kotu Isabet !");
                    System.out.println("Verilen Hasar = " + (int) ((attack + weapon.damage) / 2));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 2));
                }
                else {
                    System.out.println("Iskaladin");
                    System.out.println("Verilen Hasar = " + 0);
                }
            }

            else {
                if (different == 0) {
                    System.out.println("Alinan Hasar = " + (attack + weapon.damage));
                    enemy.health -= enemy.takeDamage(attack + weapon.damage);
                }
                else if (different > 0 && different < 3) {
                    System.out.println("Alinan Hasar = " + (int) ((attack + weapon.damage) / 1.2));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 1.2));
                }
                else if (different >= 3 && different <= 5) {
                    System.out.println("Alinan Hasar = " + (int) ((attack + weapon.damage) / 1.5));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 1.5));
                }
                else if (different > 5 && different < 9) {
                    System.out.println("Alinan Hasar = " + (int) ((attack + weapon.damage) / 2));
                    enemy.health -= enemy.takeDamage((int) ((attack + weapon.damage) / 2));
                }
                else {
                    System.out.println("Alinan Hasar = " + 0);
                }
            }

        }

        private int takeDamage(int num){

            int damage;

            if (armor == Armors.NORMAL_ARMOR){
                damage = (int)(num / Armors.NORMAL_ARMOR.protection);
            }
            else if (armor == Armors.HEAVY_ARMOR){
                damage = (int)(num / Armors.HEAVY_ARMOR.protection);
            }
            else if (armor == Armors.DARK_OF_ARMOR){
                damage = (int)(num / Armors.DARK_OF_ARMOR.protection);
            }
            else {
                damage = num;
            }

            return damage;

        }

        private enum Weapons {
            NONE(0, 0, 9999999, true),
            NORMAL_SWORD(50, 10, 10, false),
            HEAVY_SWORD(200, 20, 15, false),
            DARK_OF_BLADES(500, 30, 10,false),
            ;

            private final int cost;
            private final int damage;
            private int durability;
            private boolean isOwned;

            Weapons(int cost, int damage,int durability , boolean isOwned){
                this.cost = cost;
                this.damage = damage;
                this.isOwned = isOwned;
                this.durability = durability;
            }

            public int getDurability(){
                return this.durability;
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
            NONE(0, 1, 9999999,true),
            NORMAL_ARMOR(150, 1.1, 5,false),
            HEAVY_ARMOR(300, 1.3, 7,false),
            DARK_OF_ARMOR(500, 1.5, 4,false),
            ;

            private final int cost;
            private final double protection;
            private int durability;
            private boolean isOwned;

            Armors(int cost, double protection, int durability, boolean isOwned){
                this.cost = cost;
                this.protection = protection;
                this.isOwned = isOwned;
                this.durability = durability;
            }

            public int getDurability(){
                return this.durability;
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

            public double getProtection(){
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
