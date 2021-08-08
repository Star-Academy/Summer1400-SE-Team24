using System;

namespace InvertedIndex.Exceptions
{
    public class NotDirectoryException : Exception
    {
        public NotDirectoryException(string path) : base("Specified path ("+ path +") is not a directory") {}
    }
}