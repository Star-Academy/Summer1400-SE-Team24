using System.Collections.Generic;
using System;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Logic
{
    public class Mapper
    {
        public IDictionary<string, HashSet<Doc>> GetDocMap(Doc doc)
        {
            var map = new Dictionary<String, HashSet<Doc>>();
            var docList = new HashSet<Doc>();
            docList.Add(doc);

            foreach (var word in doc.GetWords()) {
                if(!map.ContainsKey(word))
                    map.Add(word, docList);
            }
            return map;
        }

        public virtual IDictionary<string, HashSet<Doc>> MergeMaps(IDictionary<string, HashSet<Doc>> map, IDictionary<string, HashSet<Doc>> doc)
        {
            IDictionary<string, HashSet<Doc>> newMap;

            newMap = AppendSameKeys(map, doc);
            newMap = AddDiffrentKeys(newMap, doc);

            return newMap;
        }

        public IDictionary<string, HashSet<Doc>> AppendSameKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            foreach (var pair in baseMap) {
                baseMap = AddPairValues(baseMap, additionalMap, pair);
            }
            return baseMap;
        }

        public IDictionary<string, HashSet<Doc>> AddDiffrentKeys(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap)
        {
            foreach (var pair in additionalMap) {
                baseMap = AddPair(baseMap, pair);
            }

            return baseMap;
        }

        public IDictionary<string, HashSet<Doc>> AddPair(IDictionary<string, HashSet<Doc>> baseMap, KeyValuePair<string, HashSet<Doc>> pair)
        {
            var key = pair.Key;
            if(!baseMap.ContainsKey(key)) {
                var set = new HashSet<Doc>();
                set.UnionWith(pair.Value);
                baseMap[key] = set;
            }

            return baseMap;
        }
        
        public IDictionary<string, HashSet<Doc>> AddPairValues(IDictionary<string, HashSet<Doc>> baseMap, IDictionary<string, HashSet<Doc>> additionalMap, KeyValuePair<string, HashSet<Doc>> pair)
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