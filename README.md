#ส่งที่ต้องมี
1. node version 12 [download](https://nodejs.org/dist/v12.13.0/node-v12.13.0-x64.msi)
2. Android Studio [download](https://developer.android.com/studio/?gclid=Cj0KCQjwr-_tBRCMARIsAN413WT0IaE_XnMEn36Ph0mY-4hl0nuv-sSDb5wtfWUn0_zg1E6meQTZAqoaAjQfEALw_wcB)
3. Visual Studio Code [download](https://code.visualstudio.com/)
4. yarn [download](https://yarnpkg.com/lang/en/docs/install/#windows-stable)
5. xampp [download](https://www.apachefriends.org/index.html)

#ขั้นตอนการลงฐานข้อมูล
1. เข้า xampp กด start Apache และ start MySQL
2. เข้า phpMyAdmin สำหรับสร้างฐานข้อมูล หรือ คลิก http://localhost/phpmyadmin/
3. เข้า เข้าที่ import หรือ คลิก http://localhost/phpmyadmin/server_import.php
4. กดเลือก แล้วให้เข้าไปที่โฟรเดอร์ queue-database/mydb.sql
5. กดตกลง

#ขั้นตอนการรัน backend หรือ api
1. เข้า cmd พิม cd ./queue-api หรือตำแหน่งไฟล์ queue-api
2. พิม yarn สำหรับลง node_modules 
3. พิม yarn start สำหรับ รันโปรแกรม
4. รันไว้ห้ามปิด
*** รัน api ที่ port 5000

#ขั้นตอนการรันแอพ queue
  - เนื่องจาก api อยู่ใน localhost ไม่สามารถออกเครื่องนอกได้เราจำเป็นที่จะต้องแก้ SERVER_API ทุกครั้งที่มีการเปลี่ยน network 
1. เปิดใหมดนักพัฒนาในโทรศัพท์
2. เปิด Android Studio เปิดโปรเจค queue-app แล้วเข้า build.gradle แก้ ip address http://10.0.2.2:5000/api เป็น ip address ของ network ตัวเอง
3. เสียสายโทสับเข้ากับคอมแล้ว build ลงโทรศัพท์