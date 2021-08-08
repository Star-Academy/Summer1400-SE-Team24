
using InvertedIndex.FileHandler;
using System.Collections.Generic;


namespace InvertedIndex.UserInterface
{
    public interface IUserIO
    {
        string get();

        void printResult(HashSet<Doc> result);
    }
}