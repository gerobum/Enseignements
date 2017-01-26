public class LinuxFactory implements Factory
{
    FileNameParser p;
    CountFolder c;

    public LinuxFactory()
    {
    }

    public FileNameParser CreateParseFileName()
    {
        return new ParseFileNameLinux();
    }
    
    public CountFolder CreateCountFolder()
    {
        return new CountFolderLinux();
    }
}
