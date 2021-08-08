using System.Collections.Generic;
using System;
using InvertedIndex.Keywords;

namespace InvertedIndex.Search
{
    public class QueryParser
    {
        private readonly string INCLUDE = "+";
        private readonly string EXCLUDE = "-";
        public IList<Keyword> ParseQuery(string query)
        {
            var words = query.ToLower().Split(" ");
            var keywords = new List<Keyword>();

            keywords.AddRange(getOrdinaries(words));
            keywords.AddRange(getUnions(words));
            keywords.AddRange(getExcludes(words));
            
            return keywords;
        }
        public IList<Keyword> getOrdinaries(string[] words)
        {
            var ordinaries = new List<Keyword>();
            foreach (var keyword in words) {
                if(!keyword.StartsWith(INCLUDE) && !keyword.StartsWith(EXCLUDE)) 
                    ordinaries.Add(new Ordinary(keyword));
            }
            return ordinaries;
        }
        public IList<Keyword> getUnions(string[] words)
        {
            var additionals = new List<Keyword>();
            foreach (var keyword in words) {
                if(keyword.StartsWith(INCLUDE)) 
                    additionals.Add(new Union(keyword.Substring(1)));
            }
            return additionals;
        }
        public IList<Keyword> getExcludes(string[] words)
        {
            var excludes = new List<Keyword>();
            foreach (var keyword in words) {
                if(keyword.StartsWith(EXCLUDE)) 
                    excludes.Add(new Exclude(keyword.Substring(1)));
            }
            return excludes;
        }
    }
}