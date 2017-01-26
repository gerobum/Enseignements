public class FactoryFactory
{
    public FactoryFactory()
    {
    }

   
    public Factory buildFactory(int factorytype)
    {
        switch(factorytype)
        {        
            case(0):
                return new WindowsFactory();
            
            case(1):
                return new LinuxFactory();
            
            default:
                return null;
        }
    }
}
