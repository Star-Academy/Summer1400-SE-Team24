using System;
using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.UserInterface
{
    public class ConsoleIO : IUserIO
    {
        public string get()
        {
            throw new NotImplementedException();
        }

        public void printResult(HashSet<Doc> result)
        {
            throw new NotImplementedException();
        }
    }
}