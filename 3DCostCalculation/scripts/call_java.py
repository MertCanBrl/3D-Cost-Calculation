import subprocess

def call_java_program():
    try:
        # Java programını çalıştır
        src_path = "C:\\Users\\mertb\\OneDrive\\Masaüstü\\3DCostCalculation\\src"
        result = subprocess.run(
            ['java', '-cp', src_path, 'CostCalculation'],
            check=True,
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True
        )
        print("Java programı başarıyla çalıştırıldı.")
        print("Çıktı:", result.stdout)
    except subprocess.CalledProcessError as e:
        print(f"Java programı çalıştırılırken bir hata oluştu: {e}")
        print("Hata mesajı:", e.stderr)

if __name__ == "__main__":
    call_java_program()