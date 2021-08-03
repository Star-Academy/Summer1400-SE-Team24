using System;

namespace InvertedIndex.Exceptions
{
    public class NotADirectoryException : Exception
    {
        public NotADirectoryException(string path) : base("Specified path ("+ path +") is not a directory") {}
    }
}