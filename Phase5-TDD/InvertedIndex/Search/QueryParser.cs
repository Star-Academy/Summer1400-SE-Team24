using System.Collections.Generic;
using System;
using InvertedIndex.Keywords;

namespace InvertedIndex.Search
{
    public class QueryParser
    {
        public IList<Keyword> parseQuery(string query)
        {
            throw new NotImplementedException();
        }
        public IList<Keyword> getOrdinaries(string[] words)
        {
            throw new NotImplementedException();
        }
        public IList<Keyword> getUnions(string[] words)
        {
            throw new NotImplementedException();
        }
        public IList<Keyword> getExcludes(string[] words)
        {
            throw new NotImplementedException();
        }
    }
}