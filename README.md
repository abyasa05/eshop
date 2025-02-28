## Reflection

1. Berikut merupakan implementasi kelima prinsip SOLID dalam proyek ini:
   - Dengan prinsip SRP, saya telah membagi kelas implementasi untuk `CarService` menjadi dua _subclass_ yang menangani tugas spesifik, yaitu `CarAccessImpl` yang berfungsi untuk mengakses repositori dan `CarUpdateImpl` yang berfungsi untuk memodifikasi data pada repositori.
   - Prinsip OCP diimplementasikan dengan penggunaan _interface_ untuk `CarRepository` dan `CarService`. Penggunaan _interface_ ini memungkinkan _developer_ untuk membuat beberapa implementasi tanpa harus memodifikasi satu kelas implementasi saja. Struktur kelas CarServiceImpl juga memungkinkan dilakukannya _extension_ untuk menambah kelas dengan fungsionalitas baru.
   - Prinsip LSP juga digunakan dalam pembagian kelas implementasi untuk `CarService`. Dalam hal ini, kelas utama `CarServiceImpl` hanya berfungsi untuk menyimpan `CarRepository`, sementara _subclass_-nya berisi _method-method_ untuk mengelola repositori tersebut. Dengan ini, fungsionalitas dari _subclass_ tidak akan mengubah logika dari _superclass_-nya dalam hal dilakukan substitusi _superclass_ oleh _subclass_.
   - Dengan prinsip ISP, saya juga membagi _interface_ untuk `CarService` menjadi dua bagian yang menangani tugas spesifik, yaitu `CarAccessService` yang menangani pengaksesan data dan `CarUpdateService` yang menangani modifikasi data.
   - Dengan prinsip DIP, tiap _high-level module_ kini menggunakan abstraksi dari modul dibawahnya, alih-alih menggunakan implementasi langsung. Contohnya seperti `CarServiceImpl` yang menggunakan _interface_ `CarRepository` dan `CarRepositoryImpl` yang menggunakan _interface_ `IdGeneration`.

2. Salah satu keuntungan dari penggunaan _SOLID principles_ yaitu menambah "modularitas" dari kode-kode pada proyek. Dalam kata lain, kode pada proyek tidak cenderung bersifat _rigid_, melainkan bisa dimodifikasi atau ditambahkan fungsionalitas baru tanpa perlu merombak ulang kode-kode yang sudah dibuat. Selain itu, penggunaan abstraksi sebagai "antarmuka" dari tiap modul juga membuat proses integrasi antar modul menjadi lebih fleksibel dan tidak rawan _error_. Salah satu contohnya yaitu jika implementasi struktur repositori dari proyek ini ingin diubah. Alih-alih memodifikasi kelas konkret `CarRepositoryImpl` secara langsung, _developer_ bisa membuat implementasi baru dari _interface_ `CarRepository` dan memilih implementasi tersebut untuk digunakan (salah satu caranya dengan menambahkan decorator `@Primary`). Dengan ini, fungsionalitas suatu modul dapat dimodifikasi dengan cepat tanpa meningkatkan risiko error (seperti kesalahan instansiasi pada modul lain).

3. Salah satu efek yang terjadi jika _SOLID principles_ tidak diimplementasikan yaitu modul-modul pada proyek menjadi tidak fleksibel. Hal ini bisa dikarenakan fungsionalitas dan korektifitas suatu modul sangat bergantung dengan implementasi modul lainnya. Misalnya, jika terjadi skenario seperti pada contoh sebelumnya, _developer_ harus mengubah implementasi modul `CarRepository` secara langsung, atau jika ingin membuat kelas implementasi baru, _developer_ harus mengubah instansiasi modul tersebut pada modul-modul lainnya yang bersifat _dependent_ (misalnya mengubah instansiasi `CarRepository` menjadi `CarRepository2`). Hal ini bisa meningkatkan risiko error serta mengurangi fleksibilitas kode (seandainya _developer_ ingin me-_revert_ implementasi modul atau ingin mengganti implementasi dengan cepat). Selain itu, mengenai prinsip SRP dan ISP, fungsionalitas suatu kelas atau _interface_ bisa menimbulkan ambiguitas jika tidak memiliki fungsi yang spesifik. Salah satu contohnya yaitu jika proses pembuatan ID untuk objek `Car` diimplementasikan secara langsung dalam kelas `CarRepositoryImpl` (tidak dipisah menjadi kelas sendiri). Jika seandainya terjadi suatu error yang berkaitan dengan pembuatan ID, _developer_ harus mengingat ulang mengenai letak dilakukannya proses pembuatan ID dalam keseluruhan modul pada proyek.

<br>
Link deployment aplikasi:
https://interesting-leontyne-eshop-abyasa-89124a48.koyeb.app/