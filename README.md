# ANIMAL CRUD REST API (Spring Boot)

Bu proje, Spring Boot kullanarak basit bir Hayvan (Animal) verisi üzerinde CRUD (Create, Read, Update, Delete) operasyonlarını gerçekleştiren bir Rest API örneğidir. Proje, temel Spring Boot özelliklerini, yapılandırmayı ve Rest Controller geliştirmeyi öğrenme amacıyla oluşturulmuştur.

##  Proje Hedefleri ve Uygulanan Konular

Bu uygulamada aşağıdaki temel hedefler gerçekleştirilmiştir:

1.  **Spring Boot İskeleti:** Bir Spring Boot projesi oluşturulmuştur.
2.  **Yapılandırma:** Uygulama, `application.properties` dosyasında belirtilen özel porttan (`8585`) ayağa kaldırılmıştır.
3.  **Geliştirici Araçları:** `spring-boot-devtools` bağımlılığı kullanılarak kod değişikliklerinde otomatik yeniden başlatma (hot-reload) sağlanmıştır.
4.  **Properties Kullanımı (Value Annotation):** `application.properties` dosyasında tanımlanan `course.name` ve `project.developer.fullname` değerleri, `@Value` anotasyonu kullanılarak Controller katmanına enjekte edilmiştir.
5.  **Rest API Geliştirme:** `AnimalController` sınıfı içinde bir `Map<Integer, Animal>` (key: id, value: Animal objesi) kullanılarak basit bir bellek içi (in-memory) veri depolama katmanı simüle edilmiştir.
6.  **CRUD Endpoint'leri:** Tüm temel Restful CRUD operasyonları için gerekli endpoint'ler (GET, POST, PUT, DELETE) tanımlanmıştır.

##  Teknolojiler

* **Java:** [Java Versiyonunuz (Örn: 17)]
* **Spring Boot:** 3.2.4
* **Bağımlılık Yöneticisi:** Maven
* **Mimari Stil:** RESTful Services

##  Yerel Kurulum ve Çalıştırma

Bu projeyi kendi yerel ortamınızda çalıştırmak için aşağıdaki adımları takip edin:

### Ön Gereksinimler

* Java Development Kit (JDK) [Java Versiyonunuz]
* Maven

### Adımlar

1.  **Projeyi Klonlayın:**
    ```bash
    git clone [REPO_ADRESİNİZ]
    cd fsweb-s17d1
    ```

2.  **Uygulamayı Çalıştırın:**
    Terminal üzerinden Spring Boot uygulamasını çalıştırın:
    ```bash
    mvn spring-boot:run
    ```
    Uygulama başarıyla başladıktan sonra `http://localhost:8585` adresinden erişilebilir olacaktır.

##  API Endpoint'leri

Uygulama, `/workintech/animal` temel yolu üzerinden erişilebilen bir dizi endpoint sunar.

| Metot | Endpoint | Açıklama |
| :---: | :--- | :--- |
| **GET** | `/workintech/animal` | Başlangıçta eklenenler dahil tüm hayvanları (Map değerleri) List olarak döndürür. |
| **GET** | `/workintech/animal/{id}` | Belirtilen ID'ye sahip hayvanı döndürür. |
| **POST** | `/workintech/animal` | Yeni bir hayvan objesi ekler. Request Body'de `{"id": int, "name": "string"}` formatında veri bekler. |
| **PUT** | `/workintech/animal/{id}` | İlgili ID'deki hayvanı Request Body'den gelen yeni verilerle günceller. |
| **DELETE** | `/workintech/animal/{id}` | İlgili ID'deki hayvanı Map'ten siler. |

### Örnek POST İsteği

**URL:** `http://localhost:8585/workintech/animal`
**Metot:** `POST`
**Body (Raw JSON):**
```json
{
  "id": 3,
  "name": "Kuş"
}
