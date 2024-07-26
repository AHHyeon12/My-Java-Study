import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class LottoNum {
	Random r = new Random();
	Set<Integer> nums = new TreeSet<>();
	public LottoNum(Set<Integer> nums) {
		super();
		this.nums = nums;
	}
	public Set<Integer> getNums() {
		return nums;
	}
	public void setNums(Set<Integer> nums) {
		this.nums = nums;
	}
	@Override
	public String toString() {
		return "LottoNum [nums=" + nums + "]";
	}
	
	// 1 ~ 45의 정수를 겹치지 않게 랜덤 세팅 
	public Set<Integer> randomNums(Set<Integer> nums) {
		while (nums.size() < 6) {
			nums.add(r.nextInt(45) + 1);
		}
		return nums;
	}
}
