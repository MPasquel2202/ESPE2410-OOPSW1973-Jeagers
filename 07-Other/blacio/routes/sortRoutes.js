const express = require('express');
const router = express.Router();
const SortedArray = require('../models/SortedArray');

// Sorting algorithms (Strategy Pattern)
class BubbleSort {
    sort(array) {
        const n = array.length;
        for (let i = 0; i < n - 1; i++) {
            for (let j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    [array[j], array[j + 1]] = [array[j + 1], array[j]]; // Swap
                }
            }
        }
        return array;
    }
}

class InsertionSort {
    sort(array) {
        const n = array.length;
        for (let i = 1; i < n; i++) {
            let key = array[i];
            let j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }
}

class QuickSort {
    sort(array) {
        if (array.length <= 1) {
            return array;
        }

        const pivot = array[array.length - 1];
        const left = [];
        const right = [];

        for (let i = 0; i < array.length - 1; i++) {
            if (array[i] < pivot) {
                left.push(array[i]);
            } else {
                right.push(array[i]);
            }
        }

        return [...this.sort(left), pivot, ...this.sort(right)];
    }
}

// Context for sorting
class SortingContext {
    constructor() {
        this.sortStrategy = null;
    }

    setSortStrategy(strategy) {
        this.sortStrategy = strategy;
    }

    sort(array) {
        return this.sortStrategy.sort(array);
    }
}

// Route to perform sorting
router.post('/', async (req, res) => {
    const { array } = req.body;

    if (!Array.isArray(array) || array.length <= 1) {
        return res.status(400).send("Array must have more than one element.");
    }

    const sortingContext = new SortingContext();
    let algorithm;
    
    if (array.length >= 2 && array.length <= 5) {
        sortingContext.setSortStrategy(new BubbleSort());
        algorithm = "BubbleSort";
    } else if (array.length >= 6 && array.length <= 10) {
        sortingContext.setSortStrategy(new InsertionSort());
        algorithm = "InsertionSort";
    } else {
        sortingContext.setSortStrategy(new QuickSort());
        algorithm = "QuickSort";
    }

    const sortedArray = sortingContext.sort([...array]); // Sorting with the strategy
    const unsortedArrayString = array.join(", ");

    // Save to MongoDB
    const newSortedArray = new SortedArray({
        unsorted: unsortedArrayString,
        size: array.length,
        "sort algorithm": algorithm,
        sorted: sortedArray.join(", ")
    });

    try {
        await newSortedArray.save();
        res.json({ unsorted: unsortedArrayString, size: array.length, algorithm, sorted: sortedArray });
    } catch (error) {
        console.error(error);
        res.status(500).send("Error saving data.");
    }
});

module.exports = router;
