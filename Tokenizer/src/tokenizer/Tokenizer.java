package tokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Tokenizer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dosya adini girin: ");
        String dosyaAdi = scanner.nextLine();  // Kullanıcıdan dosya adını al

        // Dosyayı işle ve aritmetik ifadeleri kelimeleştir
        try {
            aritmetikKelimeleştir(dosyaAdi);
        } catch (IOException e) {
            System.out.println("Dosya bulunamadi veya okunamadi.");
        }
    }

    // Dosyadaki aritmetik ifadeleri kelimeleştiren fonksiyon
    public static void aritmetikKelimeleştir(String dosyaAdi) throws IOException {
        // Dosyayı byte byte okuyabilmek için InputStreamReader kullanıyoruz
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dosyaAdi)));
        int karakter;

        // Sonucu tutmak için bir dinamik dizi başlatalım
        char[] sonuc = new char[100];  // Başlangıç için 100 boyutunda
        int j = 0;  // Sonuç dizisi için indeks

        // Dosyadan byte byte oku
        while ((karakter = br.read()) != -1) {  // Karakter gelmeye devam ettiği sürece oku
            // Eğer dizinin kapasitesi dolduysa büyüt
            if (j >= sonuc.length) {
                sonuc = arrayGenislet(sonuc);  // Dizi kapasitesini genişlet
            }

            // Eğer bir sayı karakteriyse
            if (karakter >= '0' && karakter <= '9') {
                while (karakter >= '0' && karakter <= '9') {
                    sonuc[j++] = (char) karakter;  // Rakamı ekle
                    karakter = br.read();  // Bir sonraki karakteri oku

                    // Dizi büyütülmeli mi kontrol et
                    if (j >= sonuc.length) {
                        sonuc = arrayGenislet(sonuc);
                    }
                }
                sonuc[j++] = ' ';  // Sayı bittiğinde bir boşluk ekle
            }

            // Operatörler ve parantezler
            if (karakter == '+' || karakter == '-' || karakter == '*' || 
                karakter == '/' || karakter == '(' || karakter == ')') {
                sonuc[j++] = (char) karakter;  // Operatörü/parantezi ekle
                sonuc[j++] = ' ';  // Boşluk ekle

                // Dizi büyütülmeli mi kontrol et
                if (j >= sonuc.length) {
                    sonuc = arrayGenislet(sonuc);
                }
            }

            // Eğer yeni bir satıra geçildiyse (satır sonu)
            if (karakter == '\n') {
                // Sonuç dizisini yazdır
                for (int k = 0; k < j; k++) {
                    System.out.print(sonuc[k]);
                }
                System.out.println();  // Yeni satır ekle
                j = 0;  // Yeni satır için indeksi sıfırla
            }
        }

        // Son satır varsa onu da yazdır (EOF durumu)
        if (j > 0) {
            for (int k = 0; k < j; k++) {
                System.out.print(sonuc[k]);
            }
            System.out.println();  // Yeni satır ekle
        }

        br.close();  // Dosyayı kapat
    }

    // Sonuç dizisinin kapasitesini genişleten fonksiyon
    public static char[] arrayGenislet(char[] eskiDizi) {
        // Eski dizinin boyutunun 2 katı olacak şekilde yeni bir dizi oluştur
        char[] yeniDizi = new char[eskiDizi.length * 2];
        // Eski dizideki tüm elemanları yeni diziye kopyala
        for (int i = 0; i < eskiDizi.length; i++) {
            yeniDizi[i] = eskiDizi[i];
        }
        return yeniDizi;
    }
}
