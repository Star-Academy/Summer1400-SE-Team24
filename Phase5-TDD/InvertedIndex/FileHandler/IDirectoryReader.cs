using System.IO;

namespace InvertedIndex.FileHandler
{
    public interface IDirectoryReader
    {
        FileInfo[] getFiles(string path);
    }    
}