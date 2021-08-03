using System;
using System.Collections.Generic;
using InvertedIndex.Keywords;
using InvertedIndex.FileHandler;
using Xunit;

namespace InvertedIndex.Test
{
    public class KeywordTest : IDisposable
    {
        private Keyword keyword;
        private HashSet<Doc> docs, newDocs, expected_result; 
        public KeywordTest()
        {
            docs = new HashSet<Doc>();
            expected_result = new HashSet<Doc>();
            docs.Add(new Doc("1", new List<string>(){"some", "words", "are", "here"}));
            docs.Add(new Doc("2", new List<string>(){"some", "another", "words"}));
            newDocs = new HashSet<Doc>();
            newDocs.Add(new Doc("1", new List<string>(){"some", "words", "are", "here"}));
            newDocs.Add(new Doc("4", new List<string>(){"here"}));
        }

        public void Dispose()
        {
            var result = keyword.Operate(docs, newDocs);
            Assert.Equal(expected_result, result);
        }

        [Fact]
        public void OrdinaryTest(){
            keyword = new Ordinary("word");
            expected_result.Add(new Doc("1", new List<string>(){"some", "words", "are", "here"}));
        }

        [Fact]
        public void ExcludeTest(){
            keyword = new Exclude("word");
            expected_result.Add(new Doc("2", new List<string>(){"some", "another", "words"}));
        
        }

        [Fact]
        public void IncludeTest(){
            keyword = new Union("word");
            expected_result.Add(new Doc("1", new List<string>(){"some", "words", "are", "here"}));
            expected_result.Add(new Doc("2", new List<string>(){"some", "another", "words"}));
            expected_result.Add(new Doc("4", new List<string>(){"here"}));
        }
    }
}