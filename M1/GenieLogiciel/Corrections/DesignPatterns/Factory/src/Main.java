public class Main
{
    public static void main_parse_filename(String path)
    {
        FactoryFactory ff = new FactoryFactory();
        Factory f = ff.buildFactory(0); //on demande a construire une factory windows
        FileNameParser p = f.CreateParseFileName();
        CountFolder c = f.CreateCountFolder();
        
        p.ParseFileName("");
        c.countfolder("");
    }
}
