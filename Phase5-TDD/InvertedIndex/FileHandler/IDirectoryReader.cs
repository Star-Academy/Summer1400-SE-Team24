using System.IO;

namespace InvertedIndex.FileHandler
{
    public interface IDirectoryReader
    {
        File[] getFiles(string path);
    }    
}