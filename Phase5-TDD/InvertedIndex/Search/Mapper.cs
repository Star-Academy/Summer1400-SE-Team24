using System.Collections.Generic;
using System;
using System.Collections;

namespace InvertedIndex.Search
{
    public class Mapper
    {
        public IDictionary<string, HashSet<Doc>> getDocMap(Doc doc)
        {
            throw new NotImplementedException();
        }

        public IDictionary<string, HashSet<Doc>> mergeMaps(IDictionary<string, HashSet<Doc>> map, IDictionary<string, HashSet<Doc>> doc)
        {
            throw new NotImplementedException();
        }

        public IDictionary<string, HashSet<Doc>> appendSameKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            throw new NotImplementedException();
        }

        public IDictionary<string, HashSet<Doc>> addDiffrentKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            throw new NotImplementedException();
        }

        public IDictionary<string, HashSet<Doc>> addPair(IDictionary<string, HashSet<Doc>> baseMap, KeyValuePair<string, HashSet<Doc>> pair)
        {
            throw new NotImplementedException();
        }
        
        public IDictionary<string, HashSet<Doc>> addPairValues(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap, KeyValuePair<string, HashSet<Doc>> pair)
        {
            throw new NotImplementedException();
        }
    }
}