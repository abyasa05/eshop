## Reflection

1. Berikut merupakan isu-isu terkait _code quality_ yang sudah saya perbaiki berdasarkan hasil _code-scanning_:
   - Dalam _unit test_ `ProductRepositoryTest`, digunakan pemanggilan _string_ yang sama secara berulang kali untuk men-_set_ ID dari _instance_ produk yang akan dites (menggunakan _method_ `setProductId`). Maka dari itu, saya kemudian mendeklarasikan _string_ ID tersebut menjadi suatu _field_ konstan untuk meningkatkan efisiensi dan mengurangi redundansi.
   - Beberapa _class_ tes (baik untuk _unit test_ maupun _functional test_) sebelumnya saya definisikan menggunakan _public visibility_. Meskipun begitu, Junit versi 5 sudah memungkinkan unit test untuk dijalankan menggunakan _default visibility_. Maka dari itu, _visibility modifier_ dari semua _class_ tes kemudian saya ubah menjadi `default` agar tes-tes ini tidak bisa diakses di luar proyek aplikasi (bersifat _package-private_).

2. Berkas-berkas _workflow_ yang sudah dibuat saat ini (didefinisikan dalam subdirektori _.github/workflows_) sebenarnya hanya mencakup proses CI saja. Hal ini dikarenakan berkas-berkas ini berfungsi untuk mengotomatisasi proses pengetesan dan validasi keamanan program ketika diintegrasikan ke repositori utama, seperti melalui _unit testing_ (ci.yml) dan _code-scanning_ (pmd.yml dan scorecard.yml). Meskipun berkas CD tidak didefinisikan secara langsung, proses untuk melakukan _build_ dan _deployment_ aplikasi ke _production environment_ bisa diotomatisasi melalui pengaturan pada platform PaaS yang digunakan (dalam hal ini menggunakan Koyeb). Maka dari itu, secara teknis, keseluruhan _workflow_ pada proyek aplikasi ini sudah meliputi proses CI/CD.


Link deployment aplikasi:
[https://interesting-leontyne-eshop-abyasa-89124a48.koyeb.app/](https://interesting-leontyne-eshop-abyasa-89124a48.koyeb.app/)