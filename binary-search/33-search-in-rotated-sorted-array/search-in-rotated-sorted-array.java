// We don't have to find the inflection point.
// O(logn) Time, O(1) Space
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                // Mid is in left sorted array.
                // We will go right side if target is either bigger than mid or smaller than the left.
                // else we will go the left side.
                if (target > nums[mid]  || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }

            } else {
                // Mid is in right sorted array.
                // We will go left side if target is smaller than the mid or target is bigger than the right.
                // else we will go right side.
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        // The code reaches here only if BS is complete and none of the mid were equal to target.
        return -1;
    }
}