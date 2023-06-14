# Created from guide: https://www.youtube.com/watch?v=bte8Er0QhDg

# What each library is for:
# cv2 - For Computer Vision
# numpy - For working with arrays
# matplotlib - For data visualization (not needed)
# tensorflow - Machine learning

import os
import cv2
import numpy as np
import matplotlib.pyplot as plt
import tensorflow as tf

# # Get data set
# mnist = tf.keras.datasets.mnist
# (x_train, y_train), (x_test, y_test) = mnist.load_data()
#
# # Normalize training data
# x_train = tf.keras.utils.normalize(x_train, axis=1)
# x_test = tf.keras.utils.normalize(x_test, axis=1)
#
# # Create Neural Network
# model = tf.keras.models.Sequential()
# model.add(tf.keras.layers.Flatten(input_shape=(28, 28)))
# model.add(tf.keras.layers.Dense(128, activation='relu'))
# model.add(tf.keras.layers.Dense(128, activation='relu'))
# model.add(tf.keras.layers.Dense(10, activation='softmax'))
#
# # Run Neural Network
# model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])
# model.fit(x_train, y_train, epochs=100)
#
# # Save Model
# model.save('handwritten.model')

# Load model
model = tf.keras.models.load_model('handwritten.model')

# # Evaluate model loss and accuracy (loss should be as low as possible, accuracy as high as possible)
# loss, accuracy = model.evaluate(x_test, y_test)
# print(loss)
# print(accuracy)


# Test Model with sample data
image_number = 1

while os.path.isfile(f"sampleDigits/digit{image_number}.png"):
    try:
        img = cv2.imread(f"sampleDigits/digit{image_number}.png")[:,:,0]
        img = np.invert(np.array([img]))
        prediction = model.predict(img)
        print(f"This digit is probably a {np.argmax(prediction)}")
        plt.imshow(img[0], cmap=plt.cm.binary)
        plt.show()

    except:
        print("Error!")

    finally:
        image_number += 1

