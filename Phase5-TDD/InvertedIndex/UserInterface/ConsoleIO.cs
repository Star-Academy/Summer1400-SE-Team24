using Internal;
using System;
using System.Transactions;

namespace InvertedIndex.UserInterface
{
    public interface IUserIO
    {
        string get();

        void printResult(HashsSet<Doc> result);
    }
}