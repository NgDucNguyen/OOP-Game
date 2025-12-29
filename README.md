<div id="top" align="center">
  <img src="res/images/ttsalpha4.0@0.5x.png" alt="Logo" width="180">
  <h1 align="center">OOP BOMBERMAN PROJECT</h1>
  <p align="center">
    <b>Trò chơi Bomberman xây dựng bằng JavaFX và Thuật toán tìm đường A*</b>
    <br />
    <br />
    <a href="#giới-thiệu">Giới thiệu</a> •
    <a href="#thành-viên">Thành viên</a> •
    <a href="#công-nghệ">Công nghệ</a> •
    <a href="#kiến-trúc">Kiến trúc</a> •
    <a href="#cài-đặt">Cài đặt</a>
  </p>
</div>

---

## 📝 Giới thiệu
Dự án được thực hiện cho môn Lập trình hướng đối tượng (OOP) tại HUST. Trò chơi mô phỏng lại gameplay kinh điển của Bomberman, áp dụng các kỹ thuật lập trình hướng đối tượng, thuật toán, chơi vô hạn  để tối ưu hóa trải nghiệm người dùng.

## 👥 Thành viên nhóm
**Team:  6 Anh em siêu nhân**

| STT | Họ và Tên | MSSV | Email | Nhiệm vụ chính |
| :---: | :--- | :---: | :--- | :--- |
| 1 | Nguyễn Đức Nguyên | 202416305 | nguyen.nd2416305@sis.hust.edu.vn | Xử lý UML |
| 2 | Đỗ Thế Công | 202416144 | cong.dt2416144@sis.hust.edu.vn | Xây dựng class Entity |
| 3 | Lê Xuân Minh | 202416284 | minh.lx2416284@sis.hust.edu.vn | Xây dựng class Control  |
| 4 | Lý Công Hiếu | 202416200 | hieu.lc2416200@sis.hust.edu.vn | Xây dựng Levels, kiếm tài nguyên |
| 5 | Phạm Duy Nguyên Lâm | 202400055 | lam.pdn2400055@sis.hust.edu.vn | Xây dựng Graphics, Features |
| 6 | Phan Minh Trúc | 202416373 | truc.pm2416373@sis.hust.edu.vn  | Báo cáo, slide |
---

## 🚀 Công nghệ và Thuật toán
* **Ngôn ngữ:** Java 11+
* **Framework:** JavaFX (Xử lý đồ họa và Sprite Sheet)
* **Quản lý dự án:** Maven
* **Thuật toán:** Sử dụng thuật toán **A* (A-Star)** giúp quái vật tìm đường truy đuổi người chơi một cách thông minh.
* **Nguyên lý OOP:** Áp dụng triệt để Đóng gói, Kế thừa, Đa hình và Trừu tượng để quản lý các thực thể trong game.

---

## 🏗 Kiến trúc mã nguồn
Mã nguồn được tổ chức theo các Package chuyên trách:

* `Entity`: Quản lý các đối tượng trong game:
    * `Animal`: Nhân vật chính (Bomber) và các loại quái vật.
        * `moving_strategy`: Chứa các thuật toán AI di chuyển cho quái vật.
    * `Block`: Tường, Gạch, Cỏ, Bom và Cổng thoát (Portal).
    * `Items`: Các vật phẩm tăng sức mạnh (SpeedItem, FlameItem).
* `Control`: Xử lý logic va chạm, di chuyển và trạng thái bàn phím.
* `Graphics`: Quản lý Sprite, SpriteSheet và hệ thống Render bản đồ.
* `Levels`: Quản lý hệ thống màn chơi từ file cấu hình.
* `Features`: Quản lý hiệu ứng âm thanh (SoundManager).

---

## 🎮 Điều khiển

| Phím | Hành động |
| :--- | :--- |
| `↑` `↓` `←` `→` | Di chuyển nhân vật |
| `Space` | Đặt bom |
| `P` | Tạm dừng (Pause) và tiếp tục (Resume) |


---

## 🛠 Cài đặt
Đảm bảo máy tính đã cài đặt JDK 11 trở lên và Maven.

1. **Sao chép dự án:**
   ```bash
   git clone https://github.com/NgDucNguyen/OOP-Game

2. **Chạy chương trình:**

* Mở dự án bằng các trình soạn thảo mã nguồn (IntelliJ IDEA, VS Code, hoặc Eclipse).
* Tìm đến file theo đường dẫn: `src/main/java/GameRunner/Launcher.java`.
* Kích chuột phải vào file **Launcher.java** và chọn **Run 'Launcher.main()'** để bắt đầu trò chơi.