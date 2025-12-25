package Levels;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Control.Menu.bomb_number;
import static Control.Menu.time_number;
import Entity.animal.Animal;
import Entity.animal.Ballom;
import Entity.animal.Bomber;
import Entity.animal.Doll;
import Entity.animal.Oneal;
import GameRunner.RunBomberman;
import Graphics.Sprite;

public class Lastlevel {
    private List<Point> grassTiles = new ArrayList<>(); // List chứa các grass
    private int currentWave = 1;
    private int width;
    private int height;
    private int[][] mapObjects;
     
    public Lastlevel(int width, int height, int[][] mapObjects) {
        this.width = width;
        this.height = height;
        this.mapObjects = mapObjects;
    }
    //Kiểm tra trong id_objects cái nào là grass để lấy ra ,đây là những vị trí mà ô trống có thể đặt enemy vào
    public void updateWalkableTiles() {
        grassTiles.clear(); // Xóa list cũ
        for (int y = 0; y < RunBomberman.height; y++) {
            for (int x = 0; x < RunBomberman.width; x++) {
                if (RunBomberman.id_objects == null) {
                    return ;
                }

            // Giả sử 0 là đất, hoặc ' ' (32) là đất. Bạn kiểm tra cả 2 cho chắc.
            int id = RunBomberman.id_objects[x][y];
            if (id == 0 ) { //Nếu id là 0 thì là cỏ 
                grassTiles.add(new Point(x, y));//Thêm vào 
            }
            }
        }
        System.out.println(grassTiles.size());
    }

    //Xây dựng chiến lược mua bán theo cấp wave liên tục tăng từ đó build được một dàn quái mới với độ khó tăng dần
    public List<Animal> prepareEnemiesForWave() {
        List<Animal> enemies = new ArrayList<>();
        int budget = 5 + (currentWave *2); // Công thức tiền

        Random rand = new Random();//Random chọn

        while (budget > 0) {
            // Random loại quái định mua
            int type = rand.nextInt(3); //Chọn random giữa số loại quái
            int cost = 0;
            Animal newEnemy = null;

            switch (type) {//Quy định tạo quái với quy luật ở dưới
                case 0: // Ballom
                    cost = 1;
                    if (budget >= cost) newEnemy = new Ballom(0, 0, Sprite.ballom_left_1.getFxImage());
                    break;
                case 1: // Oneal
                    cost = 2;
                    if (budget >= cost) newEnemy = new Oneal(0, 0, Sprite.oneal_right_1.getFxImage());
                    break;
                case 2: // Doll
                    cost = 4;
                    if (budget >= cost) newEnemy = new Doll(0, 0, Sprite.doll_left_1.getFxImage());
                    break;
            }

            if (newEnemy != null) {
                enemies.add(newEnemy);
                budget -= cost;
            } else if (budget < 2) {
                break;
            }
        }
        return enemies;
    }

    public void spawnWave(Bomber player, List<Animal> gameEnemiesList) {
        updateWalkableTiles();//Lấy ô grass
        
        if (grassTiles.isEmpty()) return;
            

        List<Animal> enemiesToSpawn = prepareEnemiesForWave();// Lấy quái 
        Random rand = new Random();

        for (Animal e : enemiesToSpawn) {
            boolean valid = false;
            int attempts = 0;
            while (!valid && attempts < 20) { //Lấy giới hạn để đỡ lag máy
                if (grassTiles.isEmpty()) break;

                int index = rand.nextInt(grassTiles.size());//Chọn random ô trống
                Point p = grassTiles.get(index);

                //Lấy thông số pixel của ô trống đó
                int spawnPixelX = p.x * 32; 
                int spawnPixelY = p.y * 32;
                
                // Tính khoảng cách
                int distance = (int)(Math.sqrt(Math.pow(spawnPixelX - player.getX(), 2) + Math.pow(spawnPixelY - player.getY(), 2)));
                //Nếu cách khoảng 150 pixel là tầm 32*5 5 ô thì chọn (Không thể để quá sát)
                if (distance > 150) { 
                    e.setX(spawnPixelX);
                    e.setY(spawnPixelY);
                    gameEnemiesList.add(e);//Thêm vào enemy list
                    
                    valid = true; 
                    grassTiles.remove(index); //Lúc này xóa ô để tránh TH 2 quái cùng 1 ô
                }
                //Thử lại lần nữa
                attempts++;
            }
        }
        for (Animal e :gameEnemiesList){
            e.setLife(true);//Set life cho quái
        }
        updateresource();//Update thông số mới
        currentWave++;//Wave mới
    }

     

    private void updateresource() {
        time_number+=20;//Cộng cho 20s và 5 quả bom
        bomb_number+=5;
    }
}
