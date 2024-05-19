class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Need to apply BS on the smaller array. Will always make array a smaller.

        // This does not increase space complexity to O(n), because it's just a reference.
        int[] a = nums1;
        int[] b = nums2;
        if (nums1.length > nums2.length) {
            a = nums2;
            b = nums1;
        }

        int totalNumbers = a.length + b.length;
        // Doing +1 here and treating the BS's mid like a partition rather than index makes life easy.
        // Basically mid = first item just after partition towards right.
        int halfNumbers = (totalNumbers + 1) / 2;

        // BS to find how many we are gonna pick from array a.
        int left = 0;
        int right = a.length;

        double result = 0.0;
        while (left <= right) {
            int partA = (left + right) >>> 1;
            int partB = halfNumbers - partA;

            // Handle the edge cases here. These will happen if we take up entier arary (a or b).
            int aLeft = (partA == 0) ? Integer.MIN_VALUE : a[partA - 1];
            int aRight = (partA == a.length) ? Integer.MAX_VALUE : a[partA];
            int bLeft = (partB == 0) ? Integer.MIN_VALUE : b[partB - 1];
            int bRight = (partB == b.length) ? Integer.MAX_VALUE : b[partB];

            // Check for perfect parition. Cross check between arrays.
            if (aLeft <= bRight && bLeft <= aRight) {
                // Partition is correct.
                if (totalNumbers % 2 == 1) {
                    // Since I am doing (total+1) / 2 for half, in case of odd, need to pick from left.
                    result = Math.max(aLeft, bLeft);
                } else {
                    // In case of even total numbers, we take max from left sides and min from right sides.
                    // And return the average.
                    result = (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2d;
                }
                break;
            } else if (aLeft > bRight) {
                // a got too many elements. Take fewer from a and take more from b.
                // So the large values from a will go towards right partition and smaller values from b
                // will come towards left partition.
                right = partA - 1;
            } else {
                // a got too few elements. Take more from a and take less from b.
                // So the large values from b will go towards right partition and smaller values from a
                // will come towards left partition.
                left = partA + 1;
            }
        }
        
        return result;
    }
}