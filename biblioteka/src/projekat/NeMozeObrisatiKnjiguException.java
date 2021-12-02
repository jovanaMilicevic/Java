package projekat;

public class NeMozeObrisatiKnjiguException extends Exception{
    public NeMozeObrisatiKnjiguException() {
        System.out.println("Knjiga je izdata, ne mozete da je obrisete");
    }
}
