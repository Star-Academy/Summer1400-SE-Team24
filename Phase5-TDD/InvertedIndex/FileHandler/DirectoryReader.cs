using System;
using System.IO;
using InvertedIndex.Exceptions;

namespace InvertedIndex.FileHandler
{
    public class DirectoryReader : IDirectoryReader
    {
        public FileInfo[] GetFiles(string dirPath)
        {
            var path = new DirectoryInfo(dirPath);
            if(path.Exists) {
                return path.GetFiles();
            }
            throw new NotDirectoryException(dirPath);
        }
    }
}