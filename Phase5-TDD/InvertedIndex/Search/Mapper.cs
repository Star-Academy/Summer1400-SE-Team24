using System.Collections.Generic;
using System;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Search
{
    public class Mapper
    {
        public IDictionary<string, HashSet<Doc>> getDocMap(Doc doc)
        {
            var map = new Dictionary<String, HashSet<Doc>>();
            var docList = new HashSet<Doc>();
            docList.Add(doc);

            foreach (var word in doc.getWords()) {
                if(!map.ContainsKey(word))
                    map.Add(word, docList);
            }
            return map;
        }

        public IDictionary<string, HashSet<Doc>> mergeMaps(IDictionary<string, HashSet<Doc>> map, IDictionary<string, HashSet<Doc>> doc)
        {
            IDictionary<string, HashSet<Doc>> newMap;

            newMap = appendSameKeys(map, doc);
            newMap = addDiffrentKeys(newMap, doc);

            return newMap;
        }

        public IDictionary<string, HashSet<Doc>> appendSameKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            foreach (var pair in baseMap) {
                baseMap = addPairValues(baseMap, additionalMap, pair);
            }
            return baseMap;
        }

        public IDictionary<string, HashSet<Doc>> addDiffrentKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            foreach (var pair in additionalMap) {
                baseMap = addPair(baseMap, pair);
            }

            return baseMap;
        }

        public IDictionary<string, HashSet<Doc>> addPair(IDictionary<string, HashSet<Doc>> baseMap, KeyValuePair<string, HashSet<Doc>> pair)
        {
            var key = pair.Key;
            if(!baseMap.ContainsKey(key)) {
                var set = new HashSet<Doc>();
                set.UnionWith(pair.Value);
                baseMap[key] = set;
            }

            return baseMap;
        }
        
        public IDictionary<string, HashSet<Doc>> addPairValues(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap, KeyValuePair<string, HashSet<Doc>> pair)
        {
            var key = pair.Key;
            if(additionalMap.ContainsKey(key)) {
                var docValues = additionalMap[key];
                var mapValues = baseMap[key];
                mapValues.UnionWith(docValues);
                baseMap[key] = mapValues;
            }

            return baseMap;
        }
    }
}