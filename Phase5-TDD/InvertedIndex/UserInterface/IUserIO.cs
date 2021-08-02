using Internal;
using System;
using System.Transactions;

namespace InvertedIndex.UserInterface
{
    public class ConsoleIO : IUserIO
    {
        public override string get()
        {
            throw new NotImplementedException();
        }

        public void printResult(HashsSet<Doc> result)
        {
            throw new NotImplementedException();
        }
    }
}