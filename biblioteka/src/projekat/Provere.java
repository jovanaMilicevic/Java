package projekat;

import java.util.ArrayList;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Provere {

    static boolean proveriKnjigu(String knjiga) throws FileNotFoundException, IOException, KnjigaPostojiException{
        ArrayList<Knjiga> knjigaProvera = new ArrayList<>();
        int br = 0;

        knjigaProvera = Knjiga.citajDat();

        for(Knjiga k : knjigaProvera)
        {
            if(k.getNaziv().equals(knjiga))
            {
                br++;
                System.out.println("Knjiga vec postoji");
            }
        }
        if(br>0)
            return true;
        else
            return false;
    }

    static boolean ulogujKorisnika(String korIME, String paSS) throws FileNotFoundException, IOException{
        ArrayList<Kupac> kupacProvera = new ArrayList<>();

        kupacProvera = Admin.citajKorisnika();
        int br = 0;

        for(Kupac k : kupacProvera)
        {
            if(k.kor_ime.equals(korIME) && k.lozinka.equals(paSS))
            {
                br++;
                break;
            }
        }
        if(br==1)
            return true;
        else
            return false;
    }

    static boolean proveriIdKnjige(int id) throws FileNotFoundException, IOException, KnjigaPostojiException
    {
        ArrayList<Knjiga> knjigaProvera = new ArrayList<>();
        int br = 0;

        knjigaProvera = Knjiga.citajDat();

        for(Knjiga k : knjigaProvera)
        {
            if(k.getId_knjige() == (id))
            {
                br++;
            }
        }
        if(br>0)
            return true;
        else
            return false;
    }

    static boolean proveraBroj(String provera)
    {
        char[] c = provera.toCharArray();
        int br = 0;
        for(char ch : c)
        {
            if(Character.isDigit(ch))
            {
                br++;
            }
        }
        if(br>0)
            return true;
        else
            return false;

    }

    static boolean proveraTelefon(String provera)
    {
        char[] c = provera.toCharArray();
        boolean prvr = true;
        for(int i=1; i < c.length; i++)
        {
            if(c[0]=='0' || c[0]=='+') {
                if (!(Character.isDigit(c[i]))) {
                    prvr = false;
                }
            }
            else
                prvr = false;
        }
        return prvr;

    }

    static boolean korImePostoji(String korIme) throws IOException {
        ArrayList<Kupac> kupacProvera = new ArrayList<>();

        kupacProvera = Admin.citajKorisnika();
        int br = 0;

        for(Kupac k : kupacProvera)
        {
            if(k.kor_ime.equals(korIme))
            {
                br++;
                break;
            }
        }
        if(br==1)
            return true;
        else
            return false;
    }

    static boolean proveriIdKupca(int id) throws FileNotFoundException, IOException, KnjigaPostojiException
    {
        ArrayList<Kupac> kupacProvera = new ArrayList<>();
        int br = 0;

        kupacProvera = Admin.citajKorisnika();

        for(Kupac k : kupacProvera)
        {
            if(k.getId_kupca() == (id))
            {
                br++;
            }
        }
        if(br>0)
            return true;
        else
            return false;
    }
}
