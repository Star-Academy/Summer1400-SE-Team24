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
        private readonly Doc DOC_TEST1 = new Doc("1", new List<string>(){"some", "words", "are", "here"});
        private readonly Doc DOC_TEST2 = new Doc("2", new List<string>(){"some", "another", "words"});
        private readonly Doc DOC_TEST3 = new Doc("1", new List<string>(){"some", "words", "are", "here"});
        private readonly Doc DOC_TEST4 = new Doc("4", new List<string>(){"here"});
        private readonly String DUMMY_WORD = "dummyword";
        public KeywordTest()
        {
            docs = new HashSet<Doc>();
            expected_result = new HashSet<Doc>();
            docs.Add(DOC_TEST1);
            docs.Add(DOC_TEST2);
            newDocs = new HashSet<Doc>();
            newDocs.Add(DOC_TEST1);
            newDocs.Add(DOC_TEST4);
        }

        public void Dispose()
        {
            var result = keyword.Operate(docs, newDocs);
            Assert.Equal(expected_result, result);
        }

        [Fact]
        public void OrdinaryTest(){
            keyword = new Ordinary(DUMMY_WORD);
            expected_result.Add(DOC_TEST1);
        }

        [Fact]
        public void ExcludeTest(){
            keyword = new Exclude(DUMMY_WORD);
            expected_result.Add(DOC_TEST2);
        
        }

        [Fact]
        public void IncludeTest(){
            keyword = new Union(DUMMY_WORD);
            expected_result.Add(DOC_TEST1);
            expected_result.Add(DOC_TEST2);
            expected_result.Add(DOC_TEST4);
        }
    }
}