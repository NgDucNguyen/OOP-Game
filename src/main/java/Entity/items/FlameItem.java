package Entity.items;

import javafx.scene.image.Image;
import Entity.Entity;
import Entity.block.Bomb;
import Graphics.Sprite;

import static GameRunner.RunBomberman.*;

public class FlameItem extends Items {

    public FlameItem(int x, int y, Image img) {
        super(x, y, img);
    }

    public FlameItem(boolean received) {
        super(received);
    }

    public FlameItem() {
    }

    @Override
    public void update() {
        for (Entity entity : block)
            if (entity instanceof FlameItem && !this.received)//Vật ẩn trong brick
                if (list_kill[entity.getX() / 32][entity.getY() / 32] == 4)//Nếu nó bị nổ thì hiện vật phẩm ra
                    entity.setImg(Sprite.powerup_flames.getFxImage());

        if (!this.received)//Lúc này người dùng lấy được vật phẩm và bomb nổ dài thêm 2 ô
            if (player.getX() == this.x && player.getY() == this.y) {
                this.setImg(Sprite.grass.getFxImage());
                this.received = true;
                Bomb.power_bomb += 2;
            }
    }
}