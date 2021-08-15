using System.IO;

namespace InvertedIndex.FileHandler
{
    public interface IDirectoryReader
    {
        FileInfo[] GetFiles(string path);
    }    
}