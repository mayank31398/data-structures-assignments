public class program
{
	public int test(char key[], char answer[])
	{
		/*
		Exercise 21: Score Marks- The â€�keyâ€� array is an array containing the correct
		answers to an exam, like {â€™aâ€™,â€™aâ€™,â€™bâ€™,â€™câ€™}. The â€�answersâ€� array contains a studentâ€™s
		answers, with â€™ ?â€™ representing a question left blank. The two arrays are not
		empty and are the same length. Return the score for the provided array of
		answers, giving a +4 for each correct answer, -1 for each incorrect answer and 0
		for each blank answer. For e.g.
		key = {â€™aâ€™,â€™câ€™,â€™dâ€™,â€™bâ€™}
		answers = {â€™câ€™,â€™câ€™,â€™ ?â€™,â€™bâ€™}
		then score is -1 + 4 + 0 + 4 = 7
		*/
		
		int n=0;

		for(int i=0;i<key.length;i++)
		{
			if(key[i]!=answer[i] && answer[i]!='?')
				n-=1;
			
			if(key[i]==answer[i])
				n+=4;
		}
		
		return n;
	}
}