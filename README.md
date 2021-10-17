
# Java Remote Control 👩🏻‍💻

Xây dựng một chương trình điều khiển từ xa có thể hiển thị màn hình của một máy tính khách (qua internet) trên màn hình máy chủ server. Chương trình cho phép sử dụng chuột và bàn phím để điều khiển PC khác từ xa. 


# ----Các tính năng chính của chương trình ✨
•	Xem màn hình từ xa
•	Đièu khiển di chuyển chuột
•	Bắt được các sự kiện của chuột để có thể ấn được các button
•	Bắt được sự kiện của bàn phím



# ----Mô hình client server ✨
•	Server: 
Máy chủ chờ kết nối từ các client và trên mỗi client được kết nối, một khung mới xuất hiện hiển thị màn hình máy client hiện tại. Khi di chuyển chuột qua khung, điều này dẫn đến việc di chuyển chuột ở phía máy khách. Cho phép điều khiển máy khách.
+ lớp ServerInitiator: Đây là lớp mục nhận lắng nghe cổng máy chủ và chờ kết nối của client. Ngoài ra, nó tạo ra một phần giao diện GUI chương trình.
+lớp ClientHandle: Với mỗi máy khách kết nối đến sẽ tạo 1 đối tượng mới trong lớp này. Hiển thị 1 thông báo cho máy khách và nhận được kích thước màn hình máy khách đó.
+ lớp ClientScreenReceive: nhận màn hình của máy khách và hiển thị
+ lớp ClientCommandSender: Lắng nghe lệnh từ máy chủ và gửi đến máy khách
+ lớp EnumCommand: Sử dụng hằng số để đại diện cho lệnh từ máy chủ


•	Client:
chức năng là gửi hiển thị màn hình máy tính của client trong mỗi khoảng thời gian được xác định trước. Ngoài ra, nhận các lệnh của máy chủ như "lệnh di chuyển chuột", sau đó thực hiện lệnh tại màn hình
+ lớp ClientInitiator: Đây là lớp khởi tạo máy khách. thiết lập kết nối với server và tạo GUI của client.
+ lớp CaptureScreen: nhận màn hình hiện tại theo 1 thgian xác định và gửi cho máy chủ
+ lớp ServerDelegate: nhận lệnh của máy chủ và thực thi.
+ lớp EnumCommands: tương tự server



# ---- Notes: sử dụng Method Class Robot trong java để handle chuột cho phép di chuyển chuột 	từ máy chủ server ứng với máy khách client


