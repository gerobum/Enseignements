public class WindowsFactory implements Factory
{
    FileNameParser p;
    CountFolder c;

    public WindowsFactory()
    {
    }

    public FileNameParser CreateParseFileName()
    {
        return new ParseFileNameWindows();
    }
    
    public CountFolder CreateCountFolder()
    {
        return new CountFolderWindows();
    }
}
