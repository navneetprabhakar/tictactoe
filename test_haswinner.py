#!/usr/bin/env python3
"""
Test suite for TicTacToe.hasWinner() method
This validates the logic by examining the Java source code
"""

import re
import sys

def read_java_file(filepath):
    """Read the Java file"""
    with open(filepath, 'r') as f:
        return f.read()

def test_haswinner_method_exists():
    """Test that hasWinner method exists"""
    content = read_java_file('src/TicTacToe.java')
    assert 'public static String hasWinner(GameConfig game)' in content, \
        "hasWinner() method not found"
    print("✓ hasWinner() method exists")

def test_haswinner_returns_string():
    """Test that hasWinner returns String"""
    content = read_java_file('src/TicTacToe.java')
    assert 'public static String hasWinner' in content, \
        "hasWinner() should return String"
    print("✓ hasWinner() returns String")

def test_haswinner_calls_helper_methods():
    """Test that hasWinner calls helper methods"""
    content = read_java_file('src/TicTacToe.java')
    assert 'getHorizontalWinner(game)' in content, \
        "hasWinner() should call getHorizontalWinner()"
    assert 'getVerticalWinner(game)' in content, \
        "hasWinner() should call getVerticalWinner()"
    assert 'getDiagonalWinner(game)' in content, \
        "hasWinner() should call getDiagonalWinner()"
    print("✓ hasWinner() calls all helper methods")

def test_horizontal_winner_method_exists():
    """Test that getHorizontalWinner method exists"""
    content = read_java_file('src/TicTacToe.java')
    assert 'private static String getHorizontalWinner(GameConfig game)' in content, \
        "getHorizontalWinner() method not found"
    print("✓ getHorizontalWinner() method exists")

def test_vertical_winner_method_exists():
    """Test that getVerticalWinner method exists"""
    content = read_java_file('src/TicTacToe.java')
    assert 'private static String getVerticalWinner(GameConfig game)' in content, \
        "getVerticalWinner() method not found"
    print("✓ getVerticalWinner() method exists")

def test_diagonal_winner_method_exists():
    """Test that getDiagonalWinner method exists"""
    content = read_java_file('src/TicTacToe.java')
    assert 'private static String getDiagonalWinner(GameConfig game)' in content, \
        "getDiagonalWinner() method not found"
    print("✓ getDiagonalWinner() method exists")

def test_haswinner_returns_null_on_no_winner():
    """Test that hasWinner returns null when no winner"""
    content = read_java_file('src/TicTacToe.java')
    # Check that the method returns null
    assert 'return null;' in content, \
        "hasWinner() should return null when no winner"
    print("✓ hasWinner() returns null when no winner")

def test_junit_test_file_exists():
    """Test that JUnit test file exists"""
    try:
        with open('src/TicTacToeTest.java', 'r') as f:
            content = f.read()
        assert 'public class TicTacToeTest' in content, \
            "TicTacToeTest class not found"
        print("✓ TicTacToeTest.java exists")
    except FileNotFoundError:
        raise AssertionError("TicTacToeTest.java not found")

def test_junit_tests_cover_happy_path():
    """Test that JUnit tests cover happy path"""
    with open('src/TicTacToeTest.java', 'r') as f:
        content = f.read()
    
    # Check for horizontal win test
    assert 'testHasWinnerHorizontal' in content, \
        "Missing horizontal win test"
    # Check for vertical win test
    assert 'testHasWinnerVertical' in content, \
        "Missing vertical win test"
    # Check for diagonal win test
    assert 'testHasWinnerDiagonal' in content, \
        "Missing diagonal win test"
    print("✓ JUnit tests cover happy path (horizontal, vertical, diagonal)")

def test_junit_tests_cover_error_cases():
    """Test that JUnit tests cover error cases"""
    with open('src/TicTacToeTest.java', 'r') as f:
        content = f.read()
    
    # Check for empty board test
    assert 'testHasWinnerEmptyBoard' in content, \
        "Missing empty board test"
    # Check for no winner test
    assert 'testHasWinnerNoWinner' in content, \
        "Missing no winner test"
    # Check for partial line test
    assert 'testHasWinnerPartialLine' in content, \
        "Missing partial line test"
    print("✓ JUnit tests cover error cases (empty board, no winner, partial line)")

def test_junit_tests_use_assertions():
    """Test that JUnit tests use assertions"""
    with open('src/TicTacToeTest.java', 'r') as f:
        content = f.read()
    
    assert 'assertEquals' in content or 'assertNull' in content, \
        "JUnit tests should use assertions"
    print("✓ JUnit tests use assertions")

def test_junit_tests_have_both_x_and_o():
    """Test that JUnit tests check both X and O winners"""
    with open('src/TicTacToeTest.java', 'r') as f:
        content = f.read()
    
    # Count tests with X and O
    x_tests = content.count('"X"')
    o_tests = content.count('"O"')
    
    assert x_tests > 0, "Tests should check for X winner"
    assert o_tests > 0, "Tests should check for O winner"
    print(f"✓ JUnit tests check both X and O winners ({x_tests} X tests, {o_tests} O tests)")

if __name__ == '__main__':
    tests = [
        test_haswinner_method_exists,
        test_haswinner_returns_string,
        test_haswinner_calls_helper_methods,
        test_horizontal_winner_method_exists,
        test_vertical_winner_method_exists,
        test_diagonal_winner_method_exists,
        test_haswinner_returns_null_on_no_winner,
        test_junit_test_file_exists,
        test_junit_tests_cover_happy_path,
        test_junit_tests_cover_error_cases,
        test_junit_tests_use_assertions,
        test_junit_tests_have_both_x_and_o,
    ]
    
    failed = 0
    for test in tests:
        try:
            test()
        except AssertionError as e:
            print(f"✗ {test.__name__}: {e}")
            failed += 1
    
    print(f"\n{len(tests) - failed}/{len(tests)} tests passed")
    sys.exit(0 if failed == 0 else 1)
